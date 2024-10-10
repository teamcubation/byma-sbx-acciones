package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.impl;

import com.teamcubation.AccionService.application.port.in.StockInPort;
import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.StockController;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.EditedStockDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockRequestDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockResponseDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper.EditedStockMapper;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper.StockRequestMapper;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper.StockResponseMapper;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/stock")
public class StockControllerImpl implements StockController {
    private final StockInPort stockService;

    public StockControllerImpl(StockInPort stockService) {
        this.stockService = stockService;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.getAllStockModelToStockResponseDTO());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(getByIdStockModelToStockResponseDTO(id));
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody StockRequestDTO stockRequestDTO) throws Exception {
        this.validNotNull(stockRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.create(StockRequestMapper.toStockToCreate(stockRequestDTO)));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) throws Exception {
        stockService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody EditedStockDTO editedStockDTO) throws Exception {
        this.validNotNull(editedStockDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(stockService.update(EditedStockMapper.toStockToUpdate(editedStockDTO,id)));
    }

    private void validNotNull(Object object) throws ValidationException {
        if (object == null) {
            throw new ValidationException("Object send is null");
        }
    }

    private List<StockResponseDTO> getAllStockModelToStockResponseDTO() {
        return stockService.getAll().stream().map(stockModel -> StockResponseMapper.toStockResponse(stockModel)).collect(Collectors.toList());
    }

    private StockResponseDTO getByIdStockModelToStockResponseDTO(long id) throws Exception {
        return StockResponseMapper.toStockResponse(stockService.findById(id));
    }
}
