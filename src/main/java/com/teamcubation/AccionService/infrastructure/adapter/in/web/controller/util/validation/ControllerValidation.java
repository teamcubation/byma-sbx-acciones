package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.util.validation;

import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.exception.InvalidStockDTOException;

public class ControllerValidation {
    public static final String INVALID_OBJECT = "Sent object is null whose class is ";

    public static void validateNotNull(Object object) throws Exception {
        if (object == null) {
            throw new InvalidStockDTOException(INVALID_OBJECT + object.getClass().getName());
        }
    }
}
