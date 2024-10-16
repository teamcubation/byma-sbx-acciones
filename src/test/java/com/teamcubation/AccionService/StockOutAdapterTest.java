package com.teamcubation.AccionService;

import com.teamcubation.AccionService.domain.exception.InvalidStockEntityException;
import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.exception.StockNotFoundException;
import com.teamcubation.AccionService.domain.model.Stock;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.entity.StockEntity;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.mapper.StockMapper;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.repository.SpringStockRepository;
import com.teamcubation.AccionService.infrastructure.adapter.out.persistance.repository.StockOutAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockOutAdapterTest {

    @Mock
    private SpringStockRepository stockRepository;

    @InjectMocks
    private StockOutAdapter stockOutAdapter;

    private Stock stock;
    private StockEntity expectedStockEntity;
    private Stock expectedStock;

    @BeforeEach
    public void setUp() {
        stock = Stock
                .builder()
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

        expectedStock = Stock
                .builder()
                .id(1L)
                .name("Amazon")
                .price(3000.0)
                .dividend(16.5)
                .build();
    }

    @Test
    public void shouldCreateStock_validStock_returnsCreatedStock() throws InvalidStockEntityException, InvalidStockModelException {
        when(stockRepository.save(any(StockEntity.class))).thenReturn(expectedStockEntity);

        Stock createdStock = stockOutAdapter.create(stock);

        assertNotNull(createdStock);
        assertEquals(expectedStock.getId(), createdStock.getId());
        assertEquals(expectedStock.getName(), createdStock.getName());
        assertEquals(expectedStock.getPrice(), createdStock.getPrice());
        assertEquals(expectedStock.getDividend(), createdStock.getDividend());

        verify(stockRepository, times(1)).save(any(StockEntity.class));
        verify(stockOutAdapter, times(1)).create(any(Stock.class));
    }

    @Test
    public void shouldThrowInvalidStockModelException_whenStockToCreateIsNull() {
        assertThrows(InvalidStockModelException.class, () -> {
            stockOutAdapter.create(null);
        });
    }

    @Test
    public void shouldThrowInvalidStockModelException_whenStockToCreateIsEmpty() {
        assertThrows(InvalidStockModelException.class, () -> {
            stockOutAdapter.create(Stock.builder().build());
        });
    }

    @Test
    public void shouldThrowInvalidStockModelException_whenStockNameToCreateIsNull() throws InvalidStockModelException {


        // Configuramos el comportamiento del mapper


        // Verificamos que el método lance la excepción esperada
        assertThrows(InvalidStockModelException.class, () -> {
            StockEntity testEntity = StockEntity.builder().name(null).price(3000.0).dividend(16.5).build();

            when(StockMapper.domainToEntity(any(Stock.class))).thenReturn(testEntity);
            Stock testModel = Stock.builder().name(null).price(3000.0).dividend(16.5).build();

            stockOutAdapter.create(testModel);
        });
    }

    @Test
    public void shouldThrowInvalidStockEntityException_whenStockEntityIsNull() {
        assertThrows(InvalidStockEntityException.class, () -> {
            when(stockRepository.save(any(StockEntity.class))).thenReturn(null);
            stockOutAdapter.create(stock);
        });
    }

    @Test
    public void shouldFindAStockById_whenTheIdExists() throws InvalidStockEntityException, StockNotFoundException {
        Optional<StockEntity> expectedStockEntityOptional = Optional.of(expectedStockEntity);

        when(stockRepository.findById(1L)).thenReturn(expectedStockEntityOptional);

        Stock foundStock = stockOutAdapter.findById(1L);

        assertNotNull(foundStock);
        assertEquals(expectedStock.getId(), foundStock.getId());
        assertEquals(expectedStock.getName(), foundStock.getName());
        assertEquals(expectedStock.getPrice(), foundStock.getPrice());
        assertEquals(expectedStock.getDividend(), foundStock.getDividend());
    }

    @Test
    public void shouldThrowStockNotFoundException_whenTheIdDoesNotExist() {
        assertThrows(StockNotFoundException.class, () -> {
            when(stockRepository.findById(1L)).thenReturn(Optional.empty());
            stockOutAdapter.findById(1L);
        });
    }


    /*La exceptcion la tira el mapper y por el if que valida que el optional no sea nulo
     * y no es posible llegar al mapper
     */
    @Test
    public void shouldThrowInvalidStockEntityException_whenStockEntityByIdIsNull() {
        assertThrows(InvalidStockEntityException.class, () -> {
            when(stockRepository.findById(1L)).thenReturn(null);
            stockOutAdapter.findById(1L);
        });
    }

    @Test
    public void shouldGetAllStocks() throws InvalidStockEntityException {
        List<StockEntity> expectedListFromRepository = new ArrayList<>();
        expectedListFromRepository
                .add(StockEntity
                        .builder()
                        .id(1L)
                        .name("Amazon")
                        .price(3000.0)
                        .dividend(16.5)
                        .build());
        expectedListFromRepository
                .add(StockEntity
                        .builder()
                        .id(2L)
                        .name("Google")
                        .price(4000.0)
                        .dividend(18.5)
                        .build());
        expectedListFromRepository
                .add(StockEntity
                        .builder()
                        .id(3L)
                        .name("Facebook")
                        .price(5000.0)
                        .dividend(20.5)
                        .build());

        when(stockRepository.findAll()).thenReturn(expectedListFromRepository);

        List<Stock> stockList = stockOutAdapter.getAll();

        assertNotNull(stockList);
        assertEquals(expectedListFromRepository.size(), stockList.size());
        assertEquals(expectedListFromRepository.get(0).getName(), stockList.get(0).getName());
        assertEquals(expectedListFromRepository.get(0).getPrice(), stockList.get(0).getPrice());
        assertEquals(expectedListFromRepository.get(0).getDividend(), stockList.get(0).getDividend());
        assertEquals(expectedListFromRepository.get(1).getName(), stockList.get(1).getName());
        assertEquals(expectedListFromRepository.get(1).getPrice(), stockList.get(1).getPrice());
        assertEquals(expectedListFromRepository.get(1).getDividend(), stockList.get(1).getDividend());
        assertEquals(expectedListFromRepository.get(2).getName(), stockList.get(2).getName());
        assertEquals(expectedListFromRepository.get(2).getPrice(), stockList.get(2).getPrice());
        assertEquals(expectedListFromRepository.get(2).getDividend(), stockList.get(2).getDividend());

    }

    @Test
    public void shouldThrowInvalidStockEntityException_whenItemsInStockListIsNull() {

        List<StockEntity> expectedListFromRepository = new ArrayList<>();
        expectedListFromRepository.add(null);
        assertThrows(InvalidStockEntityException.class, () -> {
            when(stockRepository.findAll()).thenReturn(expectedListFromRepository);
            stockOutAdapter.getAll();
        });
    }

    @Test
    public void shouldUpdateStock() throws InvalidStockEntityException, InvalidStockModelException {

        StockEntity expectedStockEntityBeforeUpdate = StockEntity
                .builder()
                .id(1L)
                .name("Amazon")
                .price(3000.0)
                .dividend(16.5)
                .build();


        when(stockRepository.save(any(StockEntity.class))).thenReturn(expectedStockEntityBeforeUpdate);
        Stock updatedStock = stockOutAdapter.update(stock);

        assertNotNull(updatedStock);
        assertEquals(expectedStockEntityBeforeUpdate.getId(), updatedStock.getId());
        assertEquals(expectedStockEntityBeforeUpdate.getName(), updatedStock.getName());
        assertEquals(expectedStockEntityBeforeUpdate.getPrice(), updatedStock.getPrice());
        assertEquals(expectedStockEntityBeforeUpdate.getDividend(), updatedStock.getDividend());
    }


    @Test
    public void shouldThrowInvalidStockModelException_whenStockModelToUpdateIsNull() {
        assertThrows(InvalidStockModelException.class, () -> {
            stockOutAdapter.update(null);
        });
    }

    @Test
    public void shouldThrowInvalidStockEntityException_whenTheUpdatedStockEntityIsNull() {
        assertThrows(InvalidStockEntityException.class, () -> {
            when(stockRepository.save(any(StockEntity.class))).thenReturn(null);
            stockOutAdapter.update(stock);
        });
    }

    @Test
    public void shouldDeleteStock() {
        stockOutAdapter.deleteById(1L);
    }

}