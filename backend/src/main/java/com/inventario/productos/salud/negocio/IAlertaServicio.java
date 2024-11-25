package com.inventario.productos.salud.negocio;

import com.inventario.productos.salud.entidades.Alertas;

import java.util.List;

public interface IAlertaServicio {
    public void registrar(Alertas alertas);
    public void eliminar(Long id);
    public List<Alertas> listar();
    public Alertas obtenerPorId(Long id);
    public void verificarFechaDeExpiracion();
}
