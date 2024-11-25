package com.inventario.productos.salud.repositorio;
import com.inventario.productos.salud.entidades.OrdenDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdenDetalleRepositorio extends JpaRepository<OrdenDetalle, Long>{

    @Query("SELECT od, (select m from Medicamentos m where m.id = od.medicamento.id) as Medicamento FROM OrdenDetalle od where od.orden.ordenId = :prefijo")
    List<OrdenDetalle> listarDetalleOrden(@Param("prefijo") Long prefijo);
}
