package com.teamcubation.AccionService.application.port.in;

import com.teamcubation.AccionService.domain.model.Accion;

import java.util.List;
import java.util.Optional;

public interface AccionInPort {

    Accion save(Accion accion);

    Optional<Accion> findById(Long id);

    List<Accion> findAll();

    Accion update(Accion accion);

    void deleteById(Long id);

}
