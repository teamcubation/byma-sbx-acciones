package com.teamcubation.AccionService.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Stock {
    @NotNull
    private Long id;
    @NotNull
    @NotBlank(message = "The name is mandatory")
    private String name;
    @NotNull
    private double price;
    @NotNull
    private double dividend;
}
