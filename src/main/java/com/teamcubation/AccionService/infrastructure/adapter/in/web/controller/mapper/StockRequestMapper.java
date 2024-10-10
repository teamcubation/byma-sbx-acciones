package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper;

import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockRequestDTO;

public class StockRequestMapper {
    public static Stock toStockToCreate(StockRequestDTO stockRequestDTO) {
        return Stock.builder().name(stockRequestDTO.getName()).price(stockRequestDTO.getPrice()).dividend(stockRequestDTO.getDividend()).build();
    }
}