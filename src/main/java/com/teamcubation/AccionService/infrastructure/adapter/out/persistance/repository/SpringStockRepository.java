package com.teamcubation.AccionService.infrastructure.adapter.out.persistance.repository;

import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringStockRepository extends JpaRepository<StockEntity, Long> {
}
