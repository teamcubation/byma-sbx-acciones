package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller;

import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.EditedStockDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface StockController {

    ResponseEntity<?> getAll();

    ResponseEntity<?> getById(@PathVariable Long id);

    ResponseEntity<?> create(@RequestBody StockDTO shareDTO);

    ResponseEntity<?> deleteById(@PathVariable Long id);

    ResponseEntity<?> update(@PathVariable Long id, @RequestBody EditedStockDTO editedStockDTO);

}
