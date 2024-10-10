package com.teamcubation.AccionService.application.service.util.validation;

import com.teamcubation.AccionService.domain.model.Stock;

public class ServiceValidation {
    public static final String INVALID_STOCK_DATA = "Invalid stock data";
    public static final String STOCK_WITH_NAME_ALREADY_EXISTS = "Stock with name {} already exists";
    public static final String STOCK_NOT_FOUND = "Stock not found";

    public static boolean isNull(Object... stock) {
        for (Object o : stock) {
            if (o == null) {
                return true;
            }
        }
        return false;
    }
    public static boolean isValid(Stock stock) {

        return !(stock.getName() != null && stock.getPrice() > 0 && stock.getDividend() > 0);
    }
}
