package com.inventario.productos.salud.negocio;

import com.inventario.productos.salud.entidades.Medicamentos;
import com.inventario.productos.salud.repositorio.MedicamentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoServicio  implements IMedicamentoServicio{

    @Autowired
    private MedicamentoRepositorio medicamentoRepositorio;

    @Override
    public Medicamentos registrar(Medicamentos medicamento) {
        return medicamentoRepositorio.save(medicamento);
    }

    @Override
    public Medicamentos actualizar(Long id, Medicamentos medicamento) {
        Medicamentos existente = medicamentoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado con ID: " + id));
        existente.setNombre(medicamento.getNombre());
        existente.setCategoria(medicamento.getCategoria());
        existente.setCodigo(medicamento.getCodigo());
        existente.setNivelMinimo(medicamento.getNivelMinimo());
        existente.setFechaExpiracion(medicamento.getFechaExpiracion());
        existente.setCantidadDisponible(medicamento.getCantidadDisponible());
        existente.setPrecio(medicamento.getPrecio());
        return medicamentoRepositorio.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        medicamentoRepositorio.deleteById(id);
    }

    @Override
    public List<Medicamentos> listar() {
        return medicamentoRepositorio.findAll();
    }

    @Override
    public Medicamentos obtenerPorId(Long id) {
        return medicamentoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado con ID: " + id));
    }

}
