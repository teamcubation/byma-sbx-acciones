package com.teamcubation.AccionService;

import com.teamcubation.AccionService.domain.exception.InvalidStockEntityException;
import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.entity.StockEntity;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.mapper.StockMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AdapterOutMapperTest {

    Stock initialStock;
    StockEntity expectedStockEntity;

    StockEntity initialEntity;
    Stock expectedStock;

    @BeforeEach
    void setUp() {
        initialStock = Stock
                .builder()
                .id(1L)
                .name("Amazon")
                .price(3000.0)
                .dividend(16.5)
                .build();

        expectedStockEntity = StockEntity
                .builder()
                .id(1L)
                .name("Amazon")
                .price(3000.0)
                .dividend(16.5)
                .build();

        initialEntity = StockEntity
                .builder()
                .id(1L)
                .name("Google")
                .price(4000.0)
                .dividend(18.5)
                .build();

        expectedStock = Stock
                .builder()
                .id(1L)
                .name("Google")
                .price(4000.0)
                .dividend(18.5)
                .build();

    }

    @Test
    public void shouldMapEntityToDomain() throws InvalidStockEntityException {
        Stock stock = StockMapper.entityToDomain(initialEntity);

        assertNotNull(stock);
        assertEquals(expectedStock, stock);
        assertEquals(expectedStock.getId(), stock.getId());
        assertEquals(expectedStock.getName(), stock.getName());
        assertEquals(expectedStock.getPrice(), stock.getPrice());
        assertEquals(expectedStock.getDividend(), stock.getDividend());
    }

    @Test
    public void shouldThrowInvalidStockEntityException_whenEntityIsNull() {
        assertThrows(InvalidStockEntityException.class, () -> StockMapper.entityToDomain(null));
    }


    @Test
    public void shouldMapDomainToEntity() throws InvalidStockModelException {
        StockEntity stockEntity = StockMapper.domainToEntity(initialStock);

        assertNotNull(stockEntity);
        assertEquals(expectedStockEntity, stockEntity);
        assertEquals(expectedStockEntity.getId(), stockEntity.getId());
        assertEquals(expectedStockEntity.getName(), stockEntity.getName());
        assertEquals(expectedStockEntity.getPrice(), stockEntity.getPrice());
        assertEquals(expectedStockEntity.getDividend(), stockEntity.getDividend());
    }

    @Test
    public void shouldThrowInvalidStockModelException_whenDomainIsNull() {
        assertThrows(InvalidStockModelException.class, () -> StockMapper.domainToEntity(null));
    }



    @Test
    public void shouldThrowInvalidStockModelException_whenPriceIsNull() {

        initialStock.setName(null);
        assertThrows(InvalidStockModelException.class, () -> StockMapper.domainToEntity(initialStock));
    }


}
