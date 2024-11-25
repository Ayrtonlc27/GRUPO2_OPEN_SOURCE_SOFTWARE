package com.inventario.productos.salud.negocio;

import com.inventario.productos.salud.entidades.Medicamentos;
//HU005
import com.inventario.productos.salud.repositorio.MedicamentosProjection;

import java.util.List;

public interface IMedicamentoServicio {
    Medicamentos registrar(Medicamentos medicamento);
    Medicamentos actualizar(Long id, Medicamentos medicamento);
    void eliminar(Long id);
    //Modificado Medicamentos a MedicamentosProjection
    List<MedicamentosProjection> listar();
    Medicamentos obtenerPorId(Long id);
    //HU005
    List<MedicamentosProjection> listadoMedicamentosPorDescripcion(String prefijo);
    List<MedicamentosProjection> listadoMedicamentosPorCodigo(Long prefijo);
}
