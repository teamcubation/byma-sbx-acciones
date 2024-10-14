package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.impl;

import com.teamcubation.AccionService.application.port.in.StockInPort;
import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.StockController;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.EditedStockDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockRequestDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockResponseDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper.EditedStockMapper;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper.StockRequestMapper;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper.StockResponseMapper;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.util.validation.ControllerValidation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/stock")
public class StockControllerImpl implements StockController {


    private final StockInPort stockService;

    public StockControllerImpl(StockInPort stockService) {
        this.stockService = stockService;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<?> getAll() throws Exception {
        log.info("Getting all stocks");
        List<StockResponseDTO> stockResponses = this.getAllStockModelToStockResponseDTO();
        log.info(stockResponses.toString());
        return ResponseEntity.status(HttpStatus.OK).body(stockResponses);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) throws Exception {
        log.info("Getting stock by id {}", id);
        StockResponseDTO stockResponseDTO = getByIdStockModelToStockResponseDTO(id);
        log.info("Stock found by id {}: {}", id,  stockResponseDTO.toString());
        return ResponseEntity.status(HttpStatus.OK).body(stockResponseDTO);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody StockRequestDTO stockRequestDTO) throws Exception {
        log.info("Creating stock {}", stockRequestDTO.toString());
        ControllerValidation.validateNotNull(stockRequestDTO);
        Stock stockCreated = stockService.create(StockRequestMapper.toStockToCreate(stockRequestDTO));
        log.info("Stock was created: {}", stockCreated.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(stockCreated);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) throws Exception {
        log.info("Deleting stock by id {}", id);
        stockService.deleteById(id);
        log.info("Stock with id was deleted: {}", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody EditedStockDTO editedStockDTO) throws Exception {
        log.info("Updating stock by id {}", id);
        ControllerValidation.validateNotNull(editedStockDTO);
        StockResponseDTO stockUpdate = StockResponseMapper.toStockResponse(stockService.update(EditedStockMapper.toStockToUpdate(editedStockDTO,id)));
        log.info("Stock was updated: {}", stockUpdate.toString());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(stockUpdate);
    }

    public List<StockResponseDTO> getAllStockModelToStockResponseDTO() throws Exception {
        List<StockResponseDTO> responseStocks = new ArrayList<>();

        for (Stock modelStock: this.stockService.getAll()) {
            responseStocks.add(StockResponseMapper.toStockResponse(modelStock));
        }

        return responseStocks;
    }

    private StockResponseDTO getByIdStockModelToStockResponseDTO(long id) throws Exception {
        return StockResponseMapper.toStockResponse(stockService.findById(id));
    }
}
