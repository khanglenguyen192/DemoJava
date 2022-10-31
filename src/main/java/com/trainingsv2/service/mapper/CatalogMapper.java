package com.trainingsv2.service.mapper;

import com.trainingsv2.domain.Catalog;
import com.trainingsv2.dto.catalog.CatalogDto;
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
