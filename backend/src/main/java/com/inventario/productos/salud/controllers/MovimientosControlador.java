package com.inventario.productos.salud.controllers;

import com.inventario.productos.salud.entidades.Movimiento;
import com.inventario.productos.salud.negocio.IMovimientoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientosControlador {

    @Autowired
    private IMovimientoServicio movimientoServicio;

    @PostMapping("/")
    public ResponseEntity<Movimiento> crear(@RequestBody Movimiento movimiento) {
        Movimiento nuevoMovimiento = movimientoServicio.crear(movimiento);
        return ResponseEntity.ok(nuevoMovimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        movimientoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> actualizar(@RequestBody Movimiento movimiento, @PathVariable Long id) {
        Movimiento movimientoActualizado = movimientoServicio.actualizar(movimiento, id);
        return ResponseEntity.ok(movimientoActualizado);
    }
    @GetMapping("/listar")
    public ResponseEntity<Iterable<Movimiento>> listar() {
        return ResponseEntity.ok(movimientoServicio.listar());
    }
}