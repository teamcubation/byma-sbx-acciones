package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.impl;

import com.teamcubation.AccionService.application.port.in.StockInPort;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.StockController;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.EditedStockDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.dto.StockRequestDTO;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper.EditedStockMapper;
import com.teamcubation.AccionService.infrastructure.adapter.in.web.controller.mapper.StockRequestMapper;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/stock")
public class StockControllerImpl implements StockController {
    private final StockInPort stockService;

    public StockControllerImpl(StockInPort stockService) {
        this.stockService = stockService;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(stockService.findById(id));
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody @Valid StockRequestDTO stockRequestDTO) throws Exception {
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

    public void validNotNull(Object object) throws ValidationException {
        if (object == null) {
            throw new ValidationException("Object send is null");
        }
    }
}
