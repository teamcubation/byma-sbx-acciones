package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper;

import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockDTO;

public class StockMapper {
    public static Stock toStock(StockDTO stockDTO) {
        return Stock.builder().name(stockDTO.getName()).price(stockDTO.getPrice()).dividend(stockDTO.getDividend()).build();
    }

    public static Stock toStock(StockDTO stockDTO, long id) {
        return StockMapper.toStock(stockDTO).setId(id);
    }
}