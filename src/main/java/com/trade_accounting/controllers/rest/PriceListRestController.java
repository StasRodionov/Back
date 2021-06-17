package com.trade_accounting.controllers.rest;

import com.trade_accounting.services.interfaces.PriceListService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "PriceList Rest Controller", description = "CRUD операции с прайс-листами")
@Api(tags = "PriceList Rest Controller")
@RequestMapping("/api/priceList")
public class PriceListRestController {

    private final PriceListService priceListService;

    public PriceListRestController(PriceListService priceListService){
        this.priceListService = priceListService;
    }

}
