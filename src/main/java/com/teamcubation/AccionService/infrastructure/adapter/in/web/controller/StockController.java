package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller;

import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.EditedStockDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface StockController {

    ResponseEntity<?> getAll();

    ResponseEntity<?> getById(@PathVariable long id) throws Exception;

    ResponseEntity<?> create(@RequestBody  StockDTO stockDTO) throws Exception;

    ResponseEntity<?> deleteById(@PathVariable long id) throws Exception;

    ResponseEntity<?> update(@PathVariable long id, @RequestBody EditedStockDTO editedStockDTO) throws Exception;

}
