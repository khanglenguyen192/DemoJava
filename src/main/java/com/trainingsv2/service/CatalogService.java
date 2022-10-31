package com.trainingsv2.service;

import com.trainingsv2.dto.catalog.CatalogDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogService {
    boolean createCatalog(CatalogDto catalogDto);

    boolean updateCatalog(CatalogDto catalogDto);

    List<CatalogDto> getAll();
}
