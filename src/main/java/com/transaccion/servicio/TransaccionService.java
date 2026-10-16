package com.transaccion.servicio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.transaccion.entidad.EstadoTransaccion;

import com.transaccion.entidad.Transaccion;
import com.transaccion.exception.PlazoReversionException;
import com.transaccion.exception.ReglaNegocioException;
import com.transaccion.exception.TransaccionNoEncontradaException;
import com.transaccion.repositorio.TransaccionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransaccionService {
	
	
	private final TransaccionRepository transaccionRepository;

    public TransaccionService(
            TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    
	
	public List<Transaccion> listarTodasLasTransacciones() {
		
		return transaccionRepository.findAll();
	}
    
    @Transactional
    public void reversar(Long id) {

        // 1. Buscar la transacción
        Transaccion transaccion = transaccionRepository
                .findById(id)
                .orElseThrow(() ->
                    new TransaccionNoEncontradaException(
                        "No existe la transacción con ID: " + id
                    )
                );

        // 2. Validar el estado actual
        if (transaccion.getEstado() != EstadoTransaccion.APROBADA) {
            throw new ReglaNegocioException(
                "La transacción solo puede reversarse "
                + "si su estado actual es APROBADA."
            );
        }

        // 3. Validar el plazo máximo de 24 horas
        LocalDateTime limite =
                transaccion.getFechaCreacion().plusHours(24);

        if (!LocalDateTime.now().isBefore(limite)) {
            throw new PlazoReversionException(
                "No se puede reversar la transacción: "
                + "se ha superado el plazo de 24 horas."
            );
        }

        // 4. Cambiar el estado
        transaccion.setEstado(EstadoTransaccion.REVERSADA);

        // 5. Persistir el cambio
        transaccionRepository.save(transaccion);
    }	

}
