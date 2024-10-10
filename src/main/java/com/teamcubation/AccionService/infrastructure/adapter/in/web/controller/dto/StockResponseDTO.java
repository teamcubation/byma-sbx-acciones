package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public class StockResponseDTO {
    private long id;
    private String name;
    private Double price;
    private Double dividend;
}
