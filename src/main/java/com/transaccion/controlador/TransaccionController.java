package com.transaccion.controlador;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.transaccion.entidad.Transaccion;
import com.transaccion.servicio.TransaccionService;

@RestController
@RequestMapping("/api/v1")
public class TransaccionController {

	
    private final TransaccionService transaccionService;

    public TransaccionController(
            TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }
    
    
    
	@GetMapping("/transacciones")
	public List<Transaccion> listarTransacciones() {	
		return transaccionService.listarTodasLasTransacciones();
	}
	
	

    @PatchMapping("/{id}/reversar")
    public ResponseEntity<Void> reversar(@PathVariable Long id) {

        transaccionService.reversar(id);

        return ResponseEntity.noContent().build();
    }	
	
}
