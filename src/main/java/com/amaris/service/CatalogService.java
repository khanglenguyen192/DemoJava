package com.amaris.service;

import com.amaris.dto.catalog.CatalogDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogService {
    boolean createCatalog(CatalogDto catalogDto);

    boolean updateCatalog(CatalogDto catalogDto);

    List<CatalogDto> getAll();
}
