package com.inventario.productos.salud.repositorio;

import com.inventario.productos.salud.entidades.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepositorio extends JpaRepository<Movimiento, Long> {
}
