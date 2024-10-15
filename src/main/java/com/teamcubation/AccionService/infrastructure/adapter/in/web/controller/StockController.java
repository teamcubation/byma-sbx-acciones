package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller;

import com.teamcubation.AccionService.domain.exception.DuplicatedStockException;
import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.UpdatedStockDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockRequestDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.exception.InvalidStockDTOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface StockController {

    ResponseEntity<?> getAll() throws Exception;

    ResponseEntity<?> getById(@PathVariable long id) throws StockNotFoundException, InvalidStockModelException;

    ResponseEntity<?> create(@RequestBody StockRequestDTO stockRequestDTO) throws DuplicatedStockException, InvalidStockModelException, InvalidStockDTOException;

    ResponseEntity<?> deleteById(@PathVariable long id) throws StockNotFoundException;

    ResponseEntity<?> update(@PathVariable long id, @RequestBody UpdatedStockDTO editedStockDTO) throws InvalidStockModelException, StockNotFoundException, DuplicatedStockException, InvalidStockDTOException;

}
