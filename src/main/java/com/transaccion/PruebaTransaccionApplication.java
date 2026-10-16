package com.transaccion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.transaccion.entidad.EstadoTransaccion;
import com.transaccion.entidad.Transaccion;
import com.transaccion.repositorio.TransaccionRepository;

@SpringBootApplication
public class PruebaTransaccionApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PruebaTransaccionApplication.class, args);
	}

	
	@Autowired
	//private EstudianteRepositorio repositorio;
	private TransaccionRepository transaccionRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
    
		
		Transaccion transaccion1 = new Transaccion();
		BigDecimal a = new BigDecimal("10.12345");
		transaccion1.setMonto(a);
		transaccion1.setFechaCreacion(LocalDateTime.now());
		transaccion1.setEstado(EstadoTransaccion.CREADA);
		long i = 12345678910L;
		transaccion1.setVersion(i);
		
		transaccionRepository.save(transaccion1);

		
		
	}

}
