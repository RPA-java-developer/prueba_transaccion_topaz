package com.transaccion;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.transaccion.entidad.EstadoTransaccion;
import com.transaccion.entidad.Transaccion;
import com.transaccion.exception.PlazoReversionException;
import com.transaccion.repositorio.TransaccionRepository;
import com.transaccion.servicio.TransaccionService;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class TransaccionServiceTest {

	
    @Mock
    private TransaccionRepository transaccionRepository;

    @InjectMocks
    private TransaccionService transaccionService;

    @Test
    void noDebeReversarCuandoHanPasadoMasDe24Horas() {

    	long i = 12345678910L;
        // Arrange: transacción aprobada de hace 25 horas
        Transaccion transaccion = new Transaccion(
                new BigDecimal("150000"),
                EstadoTransaccion.APROBADA,
                LocalDateTime.now().minusHours(25),
                i
        );

        when(transaccionRepository.findById(1L))
                .thenReturn(Optional.of(transaccion));

        // Act + Assert: debe lanzar la excepción de plazo
        assertThrows(
                PlazoReversionException.class,
                () -> transaccionService.reversar(1L)
        );

        // No debe persistir una reversión inválida
        verify(transaccionRepository, never())
                .save(any(Transaccion.class));

        // El estado debe permanecer APROBADA
        org.junit.jupiter.api.Assertions.assertEquals(
                EstadoTransaccion.APROBADA,
                transaccion.getEstado()
        );
    }	
	
}
