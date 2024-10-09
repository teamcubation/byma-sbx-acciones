package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class StockDTO {
    @NotBlank(message = "The name is mandatory")
    private String name;

    @NotNull(message = "The price is mandatory")
    private double price;

    @NotNull(message = "The dividend is mandatory")
    private double dividend;
}
