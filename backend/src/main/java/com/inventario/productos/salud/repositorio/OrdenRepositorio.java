package com.inventario.productos.salud.repositorio;

import com.inventario.productos.salud.entidades.Orden;
import com.inventario.productos.salud.entidades.OrdenDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdenRepositorio extends JpaRepository<Orden, Long> {

    @Query("SELECT o FROM Orden o")
    List<OrdenProjection> listarOrdenes();


}
