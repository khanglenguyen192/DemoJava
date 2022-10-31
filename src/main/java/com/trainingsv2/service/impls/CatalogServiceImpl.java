package com.trainingsv2.service.impls;

import com.trainingsv2.common.utils.GlobalConstants;
import com.trainingsv2.domain.Catalog;
import com.trainingsv2.dto.catalog.CatalogDto;
import com.trainingsv2.common.exception.impl.NotAllowException;
import com.trainingsv2.repository.CatalogRepository;
import com.trainingsv2.service.CatalogService;
import com.trainingsv2.service.mapper.CatalogMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
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
        return catalogRepository.insert(newCatalog) != null;
    }

    @Override
    public boolean updateCatalog(CatalogDto catalogDto) {
        var catalog = catalogRepository.findById(catalogDto.getCatalogId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, GlobalConstants.CATALOG_ID_NOT_FOUND)
        );

        catalogMapper.toCatalog(catalog, catalogDto);
        return catalogRepository.update(catalog) != null;
    }

    @Override
    public List<CatalogDto> getAll() {
        return catalogMapper.toCatalogDtos(catalogRepository.findAll());
    }
}
