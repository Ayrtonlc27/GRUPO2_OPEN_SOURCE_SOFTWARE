package com.inventario.productos.salud.repositorio;

import com.inventario.productos.salud.entidades.Medicamentos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepositorio extends JpaRepository<Medicamentos, Long> {
}
