package com.inventario.productos.salud.controllers;

import com.inventario.productos.salud.entidades.Medicamentos;
import com.inventario.productos.salud.negocio.IMedicamentoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//HU005
import com.inventario.productos.salud.repositorio.MedicamentosProjection;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoControlador {

    @Autowired
    private IMedicamentoServicio medicamentoServicio;

    @PostMapping
    public ResponseEntity<Medicamentos> registrar(@RequestBody Medicamentos medicamento) {
        return ResponseEntity.ok(medicamentoServicio.registrar(medicamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicamentos> actualizar(@PathVariable Long id, @RequestBody Medicamentos medicamento) {
        return ResponseEntity.ok(medicamentoServicio.actualizar(id, medicamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        medicamentoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    //Modificado Medicamentos a MedicamentosProjection
    @GetMapping
    public ResponseEntity<List<MedicamentosProjection>> listar() {
        return ResponseEntity.ok(medicamentoServicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamentos> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicamentoServicio.obtenerPorId(id));
    }
    //HU005
    @GetMapping("/busquedaDescripcion/{prefix}")
    public List<MedicamentosProjection> buscarPorDescripcion(@PathVariable(value = "prefix") String prefix){
        return medicamentoServicio.listadoMedicamentosPorDescripcion(prefix);
    }

    //HU005
    @GetMapping("/busquedaCodigo/{prefix}")
    public List<MedicamentosProjection> buscarPorCodigo(@PathVariable(value = "prefix") Long prefix){
        return medicamentoServicio.listadoMedicamentosPorCodigo(prefix);
    }
}
