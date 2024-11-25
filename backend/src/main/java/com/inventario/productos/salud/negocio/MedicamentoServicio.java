package com.inventario.productos.salud.negocio;

import com.inventario.productos.salud.entidades.Medicamentos;
import com.inventario.productos.salud.repositorio.MedicamentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//HU005
import org.springframework.http.HttpStatus;
import com.inventario.productos.salud.repositorio.MedicamentosProjection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

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

    //Modificado Medicamentos a MedicamentosProjection
    @Override
    public List<MedicamentosProjection> listar() {

        //return medicamentoRepositorio.findAll();
        return medicamentoRepositorio.listarMedicamentos();
    }

    @Override
    public Medicamentos obtenerPorId(Long id) {
        return medicamentoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado con ID: " + id));
    }

    //HU005
    @Override
    public List<MedicamentosProjection> listadoMedicamentosPorDescripcion(String prefijo) {

        return medicamentoRepositorio.obtenerListadoPorDescripcion(prefijo);
    }

    //HU005
    public List<MedicamentosProjection> listadoMedicamentosPorCodigo(Long prefijo) {

        return medicamentoRepositorio.obtenerListadoPorCodigo(prefijo);
    }

}
