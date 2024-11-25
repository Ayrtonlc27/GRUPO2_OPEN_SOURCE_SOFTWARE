package com.inventario.productos.salud.repositorio;

import java.math.BigDecimal;
import java.util.Date;

public interface MedicamentosProjection {
    Long getId();
    String getNombre();
    String getCategoria();
    String getCodigo();
    int getNivelMinimo();
    Date getFechaExpiracion();
    int getCantidadDisponible();
    BigDecimal getPrecio();


}
