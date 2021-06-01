package com.trade_accounting.controllers.rest;

import com.trade_accounting.services.interfaces.ProductionService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Production Rest Controller", description = "CRUD операции с производством")
@Api(tags = "Production Rest Controller")
@RequestMapping("/api/production")
public class ProductionRestController {

    private final ProductionService productionService;

    public ProductionRestController(ProductionService productionService){ this.productionService = productionService; }


}
