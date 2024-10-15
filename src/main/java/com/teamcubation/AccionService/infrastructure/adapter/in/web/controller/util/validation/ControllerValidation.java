package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.util.validation;

import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockRequestDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.UpdatedStockDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.exception.InvalidStockDTOException;

public class ControllerValidation {
    public static final String INVALID_UPDATED_STOCK = "Updated stock sent is null.";
    public static final String INVALID_STOCK_TO_CREATE = "Stock sent to create is null.";
    public static final String INVALID_STOCK_MODEL = "Stock model sent is null.";

    public static void validateUpdatedStockDTO(UpdatedStockDTO updatedStockDTO) throws InvalidStockDTOException {
        if (ControllerValidation.isNull(updatedStockDTO)) {
            throw new InvalidStockDTOException(INVALID_UPDATED_STOCK);
        }
    }

    public static void validateStockRequestDTO(StockRequestDTO stockRequestDTO) throws InvalidStockDTOException {
        if (ControllerValidation.isNull(stockRequestDTO)) {
            throw new InvalidStockDTOException(INVALID_STOCK_TO_CREATE);
        }
    }

    public static void validateStockModel(Stock stock) throws InvalidStockModelException {
        if(ControllerValidation.isNull(stock)) {
            throw new InvalidStockModelException(INVALID_STOCK_MODEL);
        }
    }

    public static boolean isNull(Object object) {
        if (object == null) {
            return true;
        }
        return false;
    }
}
