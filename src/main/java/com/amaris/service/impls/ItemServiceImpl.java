package com.amaris.service.impls;

import com.amaris.common.utils.GlobalConstants;
import com.amaris.domain.Catalog;
import com.amaris.domain.Item;
import com.amaris.dto.base.PageResponse;
import com.amaris.dto.item.ItemDto;
import com.amaris.exception.impl.NotAllowException;
import com.amaris.exception.impl.NotFoundException;
import com.amaris.dto.repository.CatalogRepository;
import com.amaris.dto.repository.ItemRepository;
import com.amaris.service.ItemService;
import com.amaris.service.mapper.ItemMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CatalogRepository catalogRepository;
    private final ItemMapper itemMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public ItemServiceImpl(ItemRepository itemRepository, CatalogRepository catalogRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.catalogRepository = catalogRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public PageResponse<ItemDto> getAllItem(Pageable page) {

        Page<Item> items = itemRepository.findAll(page);

        return new PageResponse<>(itemMapper.toItemDtos(items.toList()), page, items.getTotalPages());
    }

    @Override
    public List<ItemDto> getAllItem() {
        return itemMapper.toItemDtos(itemRepository.findAll());
    }

    @Override
    public boolean createItem(ItemDto itemDto) {
        itemRepository.findById(itemDto.getItemId()).ifPresent(item -> {
            throw new NotAllowException(GlobalConstants.DUPLICATE_ID);
        });

        Catalog catalog = catalogRepository.findById(itemDto.getCatalogId()).orElseThrow(() -> new NotFoundException(GlobalConstants.CATALOG_ID_NOT_FOUND));

        Item newItem = itemMapper.toItem(itemDto);
        newItem.setCatalog(catalog);
        return itemRepository.insert(newItem) != null;
    }

    @Override
    public boolean updateItem(ItemDto itemDto) {
        var item = itemRepository.findById(itemDto.getItemId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, GlobalConstants.ITEM_ID_NOT_FOUND));
        itemMapper.toItem(item, itemDto);

        //Catalog change
        if (!itemDto.getCatalogId().equals(item.getCatalog().getCatalogId())) {
            Catalog catalog = catalogRepository.findById(itemDto.getCatalogId()).orElseThrow(() -> new NotFoundException(GlobalConstants.CATALOG_ID_NOT_FOUND));
            item.setCatalog(catalog);
        }
        return itemRepository.update(item) != null;
    }

    @Override
    public PageResponse<ItemDto> findItem(Integer itemId, String itemName, Integer catalogId, Pageable page) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> query = criteriaBuilder.createQuery(Item.class);
        Root<Item> itemRoot = query.from(Item.class);

        List<Predicate> predicates = new ArrayList<>();
        if (itemId != null) predicates.add(criteriaBuilder.equal(itemRoot.get("itemId"), itemId));
        if (itemName != null) predicates.add(criteriaBuilder.equal(itemRoot.get("itemName"), itemName));
        if (catalogId != null)
            predicates.add(criteriaBuilder.equal(itemRoot.get("catalog").get("catalogId"), catalogId));

        if (!predicates.isEmpty()) {
            query.select(itemRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        List<Item> result = entityManager.createQuery(query).setFirstResult(page.getPageNumber()).setMaxResults(page.getPageSize()).getResultList();

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Item> booksRootCount = countQuery.from(Item.class);
        countQuery.select(criteriaBuilder.count(booksRootCount)).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

        // Fetches the count of all Books as per given criteria
        int count = Math.toIntExact(entityManager.createQuery(countQuery).setFirstResult(page.getPageNumber()).setMaxResults(page.getPageSize()).getSingleResult());

        return new PageResponse<>(itemMapper.toItemDtos(result), page, count / page.getPageSize());
    }
}
