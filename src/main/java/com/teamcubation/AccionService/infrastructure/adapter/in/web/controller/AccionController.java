package com.teamcubation.AccionService.infrastructure.adapter.in.web.controller;

import com.teamcubation.AccionService.domain.model.Accion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AccionController {

    ResponseEntity<?> getAll();

    ResponseEntity<?> getById(@PathVariable Long id);

    ResponseEntity<?> create(@RequestBody AccionDTO accion);

    ResponseEntity<?> deleteById(@PathVariable Long id);

    ResponseEntity<?> update(@PathVariable Long id, @RequestBody EditedAccionDTO editedAccionDTO);

}
