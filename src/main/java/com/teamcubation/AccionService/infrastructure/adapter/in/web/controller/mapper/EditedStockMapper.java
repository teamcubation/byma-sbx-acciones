package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper;

import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.EditedStockDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockDTO;

public class EditedStockMapper {
    public static Stock toStock(EditedStockDTO editedStockDTO, long id) {
        return Stock.builder().name(stockDTO.getName()).price(stockDTO.getPrice()).dividend(stockDTO.getDividend()).id(id).build();
    }
}
