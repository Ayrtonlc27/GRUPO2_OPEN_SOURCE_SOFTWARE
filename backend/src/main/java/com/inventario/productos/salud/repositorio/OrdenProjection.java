package com.inventario.productos.salud.repositorio;

import java.util.Date;

public interface OrdenProjection {
    Long getOrdenId();
    Date getFechaOrden();
    Date getFechaRequerida();
    String getProveedor();
}
