package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper;

import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockResponseDTO;

public class StockResponseMapper {
    public static StockResponseDTO toStockResponse(Stock stockModel) {
        return StockResponseDTO.builder().name(stockModel.getName()).price(stockModel.getPrice()).dividend(stockModel.getDividend()).id(stockModel.getId()).build();
    }
}
