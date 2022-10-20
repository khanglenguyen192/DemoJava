package com.amaris.service.impls;

import com.amaris.common.utils.GlobalConstants;
import com.amaris.domain.Catalog;
import com.amaris.dto.catalog.CatalogDto;
import com.amaris.exception.impl.NotAllowException;
import com.amaris.repository.CatalogRepository;
import com.amaris.service.CatalogService;
import com.amaris.service.mapper.CatalogMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {
    private final CatalogRepository catalogRepository;
    private final CatalogMapper catalogMapper;

    public CatalogServiceImpl(CatalogRepository catalogRepository, CatalogMapper catalogMapper) {
        this.catalogRepository = catalogRepository;
        this.catalogMapper = catalogMapper;
    }

    @Override
    public boolean createCatalog(CatalogDto catalogDto) {
        catalogRepository.findById(catalogDto.getCatalogId()).ifPresent(
                catalog -> { throw new NotAllowException(GlobalConstants.DUPLICATE_ID); }
        );

        Catalog newCatalog = catalogMapper.toCataLog(catalogDto);

        newCatalog.setCreatedDate(LocalDateTime.now());

        return catalogRepository.save(newCatalog) != null;
    }

    @Override
    public boolean updateCatalog(CatalogDto catalogDto) {
        var catalog = catalogRepository.findById(catalogDto.getCatalogId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, GlobalConstants.CATALOG_ID_NOT_FOUND)
        );

        catalogMapper.toCatalog(catalog, catalogDto);
        catalog.setModifyDate(LocalDateTime.now());
        return catalogRepository.save(catalog) != null;
    }

    @Override
    public List<CatalogDto> getAll() {
        return catalogMapper.toCatalogDtos(catalogRepository.findAll());
    }
}
