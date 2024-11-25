package com.inventario.productos.salud.controllers;

import com.inventario.productos.salud.entidades.Medicamentos;
import com.inventario.productos.salud.entidades.Orden;
import com.inventario.productos.salud.entidades.OrdenDetalle;
import com.inventario.productos.salud.negocio.MedicamentoServicio;
import com.inventario.productos.salud.negocio.OrdenServicio;
import com.inventario.productos.salud.repositorio.MedicamentosProjection;
import com.inventario.productos.salud.repositorio.OrdenDetalleProjection;
import com.inventario.productos.salud.repositorio.OrdenDetalleRepositorio;
import com.inventario.productos.salud.repositorio.OrdenProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenControlador {

    @Autowired
    private OrdenServicio ordenServicio;
    @Autowired
    private OrdenDetalleRepositorio ordenDetalleRepositorio;

    @GetMapping
    public ResponseEntity<List<OrdenProjection>> listar() {
        return ResponseEntity.ok(ordenServicio.listarOrdenes());
    }

    @GetMapping("/verDetalle/{ordenId}")
    public ResponseEntity<List<OrdenDetalle>> verDetalleOrden(@PathVariable Long ordenId) {

        return ResponseEntity.ok(ordenServicio.listarDetalleOrden(ordenId));
    }

    @PostMapping("/crear")
    public Orden crearOrden(@RequestBody Orden orden_request){
        return ordenServicio.crearOrdenMedicamentos(orden_request);
    }
}
