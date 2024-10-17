package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockResponseDTO {
    private long id;
    private String name;
    private Double price;
    private Double dividend;
}
