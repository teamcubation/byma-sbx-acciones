package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto;

import lombok.Data;

@Data
public class StockDTO {
    private String name;
    private double price;
    private double dividend;
}
