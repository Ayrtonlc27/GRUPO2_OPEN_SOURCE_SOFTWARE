package com.inventario.productos.salud.negocio;

import com.inventario.productos.salud.entidades.Medicamentos;
import com.inventario.productos.salud.entidades.Orden;
import com.inventario.productos.salud.entidades.OrdenDetalle;
import com.inventario.productos.salud.repositorio.OrdenDetalleProjection;
import com.inventario.productos.salud.repositorio.OrdenProjection;

import java.util.List;

public interface IOrdenServicio {
    Orden crearOrdenMedicamentos();
    List<OrdenProjection> listarOrdenes();
    List<OrdenDetalle> listarDetalleOrden(Long ordenId);
}