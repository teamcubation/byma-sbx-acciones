package com.teamcubation.AccionService.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Stock {
    private long id;
    private String name;
    private double price;
    private double dividend;
}
