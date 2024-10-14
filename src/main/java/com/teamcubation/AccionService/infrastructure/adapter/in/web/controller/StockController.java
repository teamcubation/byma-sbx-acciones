package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller;

import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.UpdatedStockDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface StockController {

    ResponseEntity<?> getAll() throws Exception;

    ResponseEntity<?> getById(@PathVariable long id) throws Exception;

    ResponseEntity<?> create(@RequestBody StockRequestDTO stockRequestDTO) throws Exception;

    ResponseEntity<?> deleteById(@PathVariable long id) throws Exception;

    ResponseEntity<?> update(@PathVariable long id, @RequestBody UpdatedStockDTO editedStockDTO) throws Exception;

}
