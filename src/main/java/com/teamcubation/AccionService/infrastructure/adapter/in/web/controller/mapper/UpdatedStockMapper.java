package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper;

import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.UpdatedStockDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.util.validation.ControllerValidation;

public class UpdatedStockMapper {
    public static Stock toStockToUpdate(UpdatedStockDTO updatedStockDTO, long id) throws Exception {
        ControllerValidation.validateNotNull(updatedStockDTO);
        return Stock.builder().name(updatedStockDTO.getName()).price(updatedStockDTO.getPrice()).dividend(updatedStockDTO.getDividend()).id(id).build();
    }
}
