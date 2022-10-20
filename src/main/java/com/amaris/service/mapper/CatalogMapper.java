package com.amaris.service.mapper;

import com.amaris.domain.Catalog;
import com.amaris.dto.catalog.CatalogDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatalogMapper {
    public Catalog toCataLog(CatalogDto catalogDto);

    public Catalog toCatalog(@MappingTarget Catalog catalog, CatalogDto catalogDto);

    public CatalogDto toCatalogDto(Catalog catalog);

    public List<CatalogDto> toCatalogDtos(List<Catalog> catalogs);
}
