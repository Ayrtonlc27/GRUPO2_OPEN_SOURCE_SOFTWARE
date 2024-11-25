package com.inventario.productos.salud.negocio;


import com.inventario.productos.salud.entidades.Movimiento;

import java.util.List;

public interface IMovimientoServicio {
    public Movimiento crear(Movimiento movimiento);
    public Long eliminar(Long id);
    public Movimiento actualizar(Movimiento movimiento, Long id);
    public List<Movimiento> listar();


}
