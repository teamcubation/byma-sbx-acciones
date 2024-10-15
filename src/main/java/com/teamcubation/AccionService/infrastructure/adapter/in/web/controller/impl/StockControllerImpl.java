package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.impl;

import com.teamcubation.AccionService.application.port.in.StockInPort;
import com.teamcubation.AccionService.domain.exception.DuplicatedStockException;
import com.teamcubation.AccionService.domain.exception.InvalidStockEntityException;
import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.StockController;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.UpdatedStockDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockRequestDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockResponseDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.exception.InvalidStockDTOException;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper.UpdatedStockMapper;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper.StockRequestMapper;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper.StockResponseMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/stock")
public class StockControllerImpl implements StockController {
    private static final String GETTING_ALL_STOCKS = "Getting all stocks";
    private static final String GETTING_STOCK_BY_ID = "Getting stock by id";
    private static final String STOCK_FOUND_BY_ID = "Stock found by id";
    private static final String CREATING_STOCK = "Creating stock";
    private static final String CREATED_STOCK = "Stock was created";
    private static final String DELETING_STOCK_BY_ID = "Deleting stock by id";
    private static final String DELETED_STOCK = "Stock with id was deleted";
    private static final String UPDATING_STOCK_BY_ID = "Updating stock by id";
    private static final String UPDATED_STOCK = "Stock with id was updated";

    private final StockInPort stockService;

    public StockControllerImpl(StockInPort stockService) {
        this.stockService = stockService;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<?> getAll() throws InvalidStockModelException, InvalidStockEntityException {
        log.info(GETTING_ALL_STOCKS);
        List<StockResponseDTO> stockResponses = this.getAllStockModelToStockResponseDTO();
        log.info(stockResponses.toString());
        return ResponseEntity.status(HttpStatus.OK).body(stockResponses);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) throws StockNotFoundException, InvalidStockModelException, InvalidStockEntityException {
        log.info(GETTING_STOCK_BY_ID, id);
        StockResponseDTO stockResponseDTO = getByIdStockModelToStockResponseDTO(id);
        log.info(STOCK_FOUND_BY_ID, id,  stockResponseDTO.toString());
        return ResponseEntity.status(HttpStatus.OK).body(stockResponseDTO);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody StockRequestDTO stockRequestDTO) throws InvalidStockDTOException, DuplicatedStockException, InvalidStockModelException, InvalidStockEntityException {
        log.info(CREATING_STOCK, stockRequestDTO.toString());
        Stock stockCreated = stockService.create(StockRequestMapper.toStockToCreate(stockRequestDTO));
        log.info(CREATED_STOCK, stockCreated.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(stockCreated);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) throws StockNotFoundException, InvalidStockEntityException, InvalidStockModelException {
        log.info(DELETING_STOCK_BY_ID, id);
        stockService.deleteById(id);
        log.info(DELETED_STOCK, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody UpdatedStockDTO updatedStockDTO) throws InvalidStockDTOException, DuplicatedStockException, StockNotFoundException, InvalidStockModelException, InvalidStockEntityException {
        log.info(UPDATING_STOCK_BY_ID, id);
        StockResponseDTO stockUpdate = StockResponseMapper.toStockResponse(stockService.update(UpdatedStockMapper.toStockToUpdate(updatedStockDTO,id)));
        log.info(UPDATED_STOCK, id, stockUpdate.toString());
        return ResponseEntity.status(HttpStatus.OK).body(stockUpdate);
    }

    public List<StockResponseDTO> getAllStockModelToStockResponseDTO() throws InvalidStockModelException, InvalidStockEntityException {
        List<StockResponseDTO> responseStocks = new ArrayList<>();

        for (Stock modelStock: this.stockService.getAll()) {
            responseStocks.add(StockResponseMapper.toStockResponse(modelStock));
        }

        return responseStocks;
    }

    private StockResponseDTO getByIdStockModelToStockResponseDTO(long id) throws StockNotFoundException, InvalidStockModelException, InvalidStockEntityException {
        return StockResponseMapper.toStockResponse(stockService.findById(id));
    }
}