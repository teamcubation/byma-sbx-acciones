package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper;

import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.EditedStockDTO;

public class EditedStockMapper {
    public static Stock toStockToUpdate(EditedStockDTO editedStockDTO, long id) {
        return Stock.builder().name(editedStockDTO.getName()).price(editedStockDTO.getPrice()).dividend(editedStockDTO.getDividend()).id(id).build();
    }
}
