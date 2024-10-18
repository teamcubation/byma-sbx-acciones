package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdatedStockDTO {
    @NotBlank(message = "The name is mandatory")
    private String name;

    @NotNull(message = "The price is mandatory")
    private Double price;

    @NotNull(message = "The dividend is mandatory")
    private Double dividend;
}
