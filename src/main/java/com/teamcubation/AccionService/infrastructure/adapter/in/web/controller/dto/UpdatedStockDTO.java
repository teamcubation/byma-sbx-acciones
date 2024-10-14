package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto;

import lombok.Data;

@Data
public class UpdatedStockDTO {
    private String name;
    private Double price;
    private Double dividend;
}
