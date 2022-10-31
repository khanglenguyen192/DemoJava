package com.trainingsv2.service.impls;

import com.trainingsv2.common.utils.GlobalConstants;
import com.trainingsv2.domain.Catalog;
import com.trainingsv2.domain.Item;
import com.trainingsv2.dto.base.PageResponse;
import com.trainingsv2.dto.item.ItemDto;
import com.trainingsv2.common.exception.impl.NotAllowException;
import com.trainingsv2.common.exception.impl.NotFoundException;
import com.trainingsv2.repository.CatalogRepository;
import com.trainingsv2.repository.ItemRepository;
import com.trainingsv2.service.ItemService;
import com.trainingsv2.service.mapper.ItemMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
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
        itemRepository.findById(itemDto.getItemId()).ifPresent(
                item -> { throw new NotAllowException(GlobalConstants.DUPLICATE_ID); }
        );

        Catalog catalog = catalogRepository.findById(itemDto.getCatalogId()).orElseThrow(
                () -> new NotFoundException(GlobalConstants.CATALOG_ID_NOT_FOUND)
        );

        Item newItem = itemMapper.toItem(itemDto);
        newItem.setCatalogId(catalog.getCatalogId());
        return itemRepository.insert(newItem) != null;
    }

    @Override
    public boolean updateItem(ItemDto itemDto) {
        var item = itemRepository.findById(itemDto.getItemId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, GlobalConstants.ITEM_ID_NOT_FOUND)
        );
        itemMapper.toItem(item, itemDto);

        //Catalog change
        if (!itemDto.getCatalogId().equals(item.getCatalogId()))
        {
            Catalog catalog = catalogRepository.findById(itemDto.getCatalogId()).orElseThrow(
                    () -> new NotFoundException(GlobalConstants.CATALOG_ID_NOT_FOUND)
            );
            item.setCatalogId(catalog.getCatalogId());
        }
        return itemRepository.update(item) != null;
    }

    @Override
    public PageResponse<ItemDto> findItem(Integer itemId, String itemName, Integer catalogId, String catalogName, String createBy, Pageable page) {
//        var qItem = QItem.item;
//        var qCatalog = QCatalog.catalog;
//        var query = new JPAQuery<Item>(entityManager);
//
//        query.from(qItem).innerJoin(qCatalog).on(qItem.catalogId.eq(qCatalog.catalogId));
//
//        if (itemId != null)
//            query.where(qItem.itemId.eq(itemId));
//
//        if (itemName != null)
//            query.where(qItem.itemName.eq(itemName));
//
//        if (catalogId != null)
//            query.where(qItem.catalogId.eq(catalogId));
//
//        if (catalogName != null)
//            query.where(qCatalog.catalogName.eq(catalogName));
//
//        if (createBy != null)
//            query.where(qItem.createdBy.eq(createBy));
//
//        Long count = query.stream().count();
//
//        query.offset(page.getOffset()).limit(page.getPageSize());
//
//        List<ItemDto> result = query.select(Projections.fields(ItemDto.class, qItem.itemId, qItem.itemName, qItem.description, qItem.catalogId)).fetch();
//
//        long totalPage = count / page.getPageSize() + (count % page.getPageSize() == 0 ? 0: 1);
//
//        return new PageResponse<>(result, page, totalPage);
        return null;
    }
}
