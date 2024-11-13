package com.inventario.productos.salud.negocio;

import com.inventario.productos.salud.entidades.Medicamentos;

import java.util.List;

public interface IMedicamentoServicio {
    Medicamentos registrar(Medicamentos medicamento);
    Medicamentos actualizar(Long id, Medicamentos medicamento);
    void eliminar(Long id);
    List<Medicamentos> listar();
    Medicamentos obtenerPorId(Long id);
}
