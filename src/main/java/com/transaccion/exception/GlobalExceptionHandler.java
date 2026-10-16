package com.transaccion.exception;

import java.util.Map;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.OptimisticLockException;



@RestControllerAdvice
public class GlobalExceptionHandler {

	
    @ExceptionHandler(TransaccionNoEncontradaException.class)
    public ResponseEntity<Map<String, String>> manejarNoEncontrada(
            TransaccionNoEncontradaException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("mensaje", ex.getMessage()));
    }

    @ExceptionHandler(ReglaNegocioException.class)
    public ResponseEntity<Map<String, String>> manejarReglaNegocio(
            ReglaNegocioException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("mensaje", ex.getMessage()));
    }

    @ExceptionHandler(PlazoReversionException.class)
    public ResponseEntity<Map<String, String>> manejarPlazo(
            PlazoReversionException ex) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(Map.of("mensaje", ex.getMessage()));
    }

    @ExceptionHandler({
        OptimisticLockException.class,
        OptimisticLockingFailureException.class
    })
    public ResponseEntity<Map<String, String>> manejarConcurrencia(
            RuntimeException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of(
                    "mensaje",
                    "La transacción fue modificada concurrentemente. "
                    + "Consulta su estado e intenta nuevamente."
                ));
    }
    
}
