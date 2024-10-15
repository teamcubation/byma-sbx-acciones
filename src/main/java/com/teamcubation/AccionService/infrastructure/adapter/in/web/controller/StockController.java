package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller;

import com.teamcubation.AccionService.domain.exception.DuplicatedStockException;
import com.teamcubation.AccionService.domain.exception.InvalidStockEntityException;
import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.UpdatedStockDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.exception.InvalidStockDTOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Stock", description = "Stock API")
public interface StockController {
    @Operation(summary = "Get all known stocks.", description = "Get all stocks which have been safed in database.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Get all stocks successfully.")})
    ResponseEntity<?> getAll() throws InvalidStockModelException, InvalidStockEntityException;

    @Operation(summary = "Get stock by id.", description = "Get stock whose id is searched.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Get stock by id successfully.")})
    ResponseEntity<?> getById(@PathVariable long id) throws StockNotFoundException, InvalidStockModelException, InvalidStockEntityException;

    @Operation(summary = "Create a new stock.", description = "Create a new stock in order to save in database.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Stock created successfully.")})
    ResponseEntity<?> create(@RequestBody StockRequestDTO stockRequestDTO) throws DuplicatedStockException, InvalidStockModelException, InvalidStockDTOException, InvalidStockEntityException;

    @Operation(summary = "Delete stock by id.", description = "Delete a stock by id which had been saved in database.")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Stock deleted successfully.")})
    ResponseEntity<?> deleteById(@PathVariable long id) throws StockNotFoundException, InvalidStockEntityException, InvalidStockModelException;

    @Operation(summary = "Update stock by id.", description = "Update a stock by id which have been safed in database.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Stock updated successfully.")})
    ResponseEntity<?> update(@PathVariable long id, @RequestBody UpdatedStockDTO updatedStockDTO) throws InvalidStockModelException, StockNotFoundException, DuplicatedStockException, InvalidStockDTOException, InvalidStockEntityException;;
}
