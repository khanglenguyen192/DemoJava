package com.trainingsv2.web.rest;

import com.trainingsv2.common.utils.GlobalConstants;
import com.trainingsv2.dto.catalog.CatalogDto;
import com.trainingsv2.service.impls.CatalogServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/catalogs")
public class CatalogController {
    private final CatalogServiceImpl catalogService;

    public CatalogController(CatalogServiceImpl catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public ResponseEntity<List<CatalogDto>> getAll() {
        return new ResponseEntity<>(catalogService.getAll(), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<String> createCatalog(@RequestBody @Valid CatalogDto request) {
        if (catalogService.createCatalog(request))
            return new ResponseEntity<>(GlobalConstants.SUCCESS, HttpStatus.OK);

        return new ResponseEntity<>(GlobalConstants.FAILED, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateCatalog(@RequestBody @Valid CatalogDto request) {
        if (catalogService.updateCatalog(request))
            return new ResponseEntity<>(GlobalConstants.SUCCESS, HttpStatus.OK);

        return new ResponseEntity<>(GlobalConstants.FAILED, HttpStatus.BAD_REQUEST);
    }
}
