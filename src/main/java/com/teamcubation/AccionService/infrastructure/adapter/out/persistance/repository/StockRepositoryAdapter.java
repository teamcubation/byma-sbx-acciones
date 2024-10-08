package com.teamcubation.AccionService.infrastructure.adapter.out.persistance.repository;

import com.teamcubation.AccionService.domain.model.Accion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StockRepositoryAdapter implements StockRepositoryPort {
    private final SpringStockRepository accionRepository;

    public StockRepositoryAdapter(SpringStockRepository accionRepository) {
        this.accionRepository = accionRepository;
    }

    Accion create(Accion accion) {
        return this.accionRepository.save(accion);
    }

    Optional<Accion> findById(Long id) {
        return this.findById(id);
    }

    List<Accion> getAll() {
        return this.accionRepository.findAll();
    }

    Accion update(Accion accion) {
        return this.accionRepository.save(accion);
    }

    void deleteById(Long id) {
        this.accionRepository.deleteById(id);
    }
}
