package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.util.validation;

import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockRequestDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.UpdatedStockDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.exception.InvalidStockDTOException;

public class ControllerValidation {
    public static final String INVALID_OBJECT = "Sent object is null whose class is ";

    public static void validateNotNull(Object object) throws Exception {
        if (object == null) {
            throw new InvalidStockDTOException(INVALID_OBJECT + object.getClass().getName());
        }
    }

    public static void validateUpdatedStockDTO(UpdatedStockDTO updatedStockDTO) throws InvalidStockDTOException {
        if (ControllerValidation.isNull(updatedStockDTO)) {
            throw new InvalidStockDTOException("Updated stock sent is null.");
        }
    }

    public static void validateStockRequestDTO(StockRequestDTO stockRequestDTO) throws InvalidStockDTOException {
        if (ControllerValidation.isNull(stockRequestDTO)) {
            throw new InvalidStockDTOException("Stock sent to create is null.");
        }
    }

    public static void validateStockModel(Stock stock) throws InvalidStockModelException {
        if(ControllerValidation.isNull(stock)) {
            throw new InvalidStockModelException("Stock model sent is null.");
        }
    }

    public static boolean isNull(Object object) {
        if (object == null) {
            return true;
        }
        return false;
    }
}
