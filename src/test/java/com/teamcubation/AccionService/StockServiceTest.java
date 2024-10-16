package com.teamcubation.AccionService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.teamcubation.AccionService.application.port.out.StockOutPort;
import com.teamcubation.AccionService.application.service.StockService;
import com.teamcubation.AccionService.domain.exception.DuplicatedStockException;
import com.teamcubation.AccionService.domain.exception.InvalidStockEntityException;
import com.teamcubation.AccionService.domain.exception.InvalidStockModelException;
import com.teamcubation.AccionService.domain.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private StockOutPort stockOutPort; // Esto es una interfaz, StockOutAdapter la implementa

    @InjectMocks
    private StockService stockService; // Clase que quieres testear

    private Stock stock;
    private Stock expectedStock;

    @BeforeEach
    void setUp() {
        // Inicializa el objeto Stock
        stock = Stock
                .builder()
                .id(1L)
                .name("Amazon")
                .price(3000.0)
                .dividend(16.5)
                .build();

        // Inicializa la entidad StockEntity si es necesario para la simulación
        expectedStock = Stock
                .builder()
                .id(1L)
                .name("Amazon")
                .price(3000.0)
                .dividend(16.5)
                .build();

    }

    @Test
    void shouldCreateStock_validStock_returnsCreatedStock() throws InvalidStockModelException, InvalidStockEntityException, DuplicatedStockException {
        // Configura el mock para devolver un objeto Stock cuando se llame a create() en stockOutPort
        when(stockOutPort.create(stock)).thenReturn(expectedStock);

        // Llama al método createStock() en StockService
        Stock createdStock = stockService.create(stock);

        assertNotNull(createdStock, "El stock creado no debería ser nulo");
        assertEquals(expectedStock.getId(), createdStock.getId(), "Los IDs deberían coincidir");
        assertEquals(expectedStock.getName(), createdStock.getName(), "Los nombres deberían coincidir");
        assertEquals(expectedStock.getPrice(), createdStock.getPrice(), "Los precios deberían coincidir");
        assertEquals(expectedStock.getDividend(), createdStock.getDividend(), "Los dividendos deberían coincidir");

        // Verifica que el método del mock fue llamado una vez
        verify(stockOutPort, times(1)).create(stock);
    }
}
