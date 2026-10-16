package com.transaccion.entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name="transacciones")
public class Transaccion {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    private EstadoTransaccion estado;

    private LocalDateTime fechaCreacion;

    @Version
    private Long version;

    public Transaccion() {
    }

    public Transaccion(
            BigDecimal monto,
            EstadoTransaccion estado,
            LocalDateTime fechaCreacion,
            Long version) {
        this.monto = monto;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.version = version;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public EstadoTransaccion getEstado() {
		return estado;
	}

	public void setEstado(EstadoTransaccion estado) {
		this.estado = estado;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}	

    
    
    
    
}
