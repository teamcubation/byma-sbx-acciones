package com.teamcubation.AccionService.infrastructure.adapter.out.persistance.mapper;

import com.teamcubation.AccionService.domain.exception.*;
import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.entity.StockEntity;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.util.validation.OutValidation;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class StockMapper {

    public static Stock entityToDomain(StockEntity stockEntity) throws InvalidStockEntityException {
        OutValidation.validateStockEntityIsNull(stockEntity);
        return Stock.builder()
                .id(stockEntity.getId())
                .name(stockEntity.getName())
                .price(stockEntity.getPrice())
                .dividend(stockEntity.getDividend())
                .build();
    }

    public static StockEntity domainToEntity(Stock stock) throws InvalidStockModelException {
        OutValidation.validateStockIsNull(stock);
        return StockEntity.builder()
                .id(stock.getId())
                .name(stock.getName())
                .price(stock.getPrice())
                .dividend(stock.getDividend())
                .build();
    }
}
