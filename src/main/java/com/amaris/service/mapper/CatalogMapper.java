package com.amaris.service.mapper;

import com.amaris.domain.Catalog;
import com.amaris.dto.catalog.CatalogDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatalogMapper {
    Catalog toCataLog(CatalogDto catalogDto);

    Catalog toCatalog(@MappingTarget Catalog catalog, CatalogDto catalogDto);

    CatalogDto toCatalogDto(Catalog catalog);

    List<CatalogDto> toCatalogDtos(List<Catalog> catalogs);
}
