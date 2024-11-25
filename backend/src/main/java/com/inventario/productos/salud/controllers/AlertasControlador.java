package com.inventario.productos.salud.controllers;

import com.inventario.productos.salud.entidades.Alertas;
import com.inventario.productos.salud.negocio.IAlertaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
public class AlertasControlador {
    @Autowired
    private IAlertaServicio alertaServicio;

    @GetMapping("/listar")
    public ResponseEntity<List<Alertas>> listar() {
        List<Alertas> alertas = alertaServicio.listar();

        return ResponseEntity.ok(alertas);
    }

}
