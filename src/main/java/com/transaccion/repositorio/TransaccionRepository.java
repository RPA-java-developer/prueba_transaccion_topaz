package com.transaccion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transaccion.entidad.Transaccion;



@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long>{

}
