package com.inventario.productos.salud.repositorio;

import com.inventario.productos.salud.entidades.Medicamentos;
import com.inventario.productos.salud.entidades.Orden;

import java.math.BigDecimal;
import java.util.Date;

public interface OrdenDetalleProjection {
   Medicamentos getMedicamento();
   Orden getOrden();
}
