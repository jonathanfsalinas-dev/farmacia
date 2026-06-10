package com.farmacia.repository;
// Esta interfaz representa el repositorio para la entidad "Movimiento",
//  que extiende JpaRepository para proporcionar métodos CRUD y de consulta para la entidad Movimiento. 

import com.farmacia.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}