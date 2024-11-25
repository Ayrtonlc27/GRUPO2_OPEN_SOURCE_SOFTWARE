package com.inventario.productos.salud.repositorio;

import com.inventario.productos.salud.entidades.Alertas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AlertasRepositorio extends JpaRepository<Alertas, Long> {

    @Query("select A from Alertas  A where A.tipo_alerta ='nivel_minimo' and  A.id_medicamento = :id_medicamento")
    public Optional<Alertas> obtenerPorIdMedicamentoAndTipoAlerta(@Param("id_medicamento") Long id_medicamento);
}
