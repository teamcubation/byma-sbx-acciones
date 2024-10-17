package com.teamcubation.AccionService.infrastructure.adapter.out.persistance.util.validation;

import com.teamcubation.AccionService.domain.exception.*;
import com.teamcubation.AccionService.domain.model.Stock;

import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.entity.StockEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OutValidation {
    public static final String INVALID_STOCK_MODEL_DATA = "Invalid stock model data";
    public static final String INVALID_STOCK_ENTITY_DATA = "Invalid stock entity data";
    public static final String STOCK_NOT_FOUND = "Stock not found";

    public static void validateStockIsNull(Stock stock) throws InvalidStockModelException {
        if (stock == null) {
            log.error(INVALID_STOCK_MODEL_DATA);
            throw new InvalidStockModelException(INVALID_STOCK_MODEL_DATA);
        }
    }

    public static void validateStockEntityIsNull(StockEntity stockEntity) throws InvalidStockEntityException {
        if (stockEntity == null) {
            log.error(INVALID_STOCK_ENTITY_DATA);
            throw new InvalidStockEntityException(INVALID_STOCK_ENTITY_DATA);
        }
    }
}
