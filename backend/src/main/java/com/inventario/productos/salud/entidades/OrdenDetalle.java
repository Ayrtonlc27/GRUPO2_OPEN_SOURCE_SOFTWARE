package com.inventario.productos.salud.entidades;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ordenDetalle")
public class OrdenDetalle {

    @Id
    @Column(name = "detalle_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detalleId;

    @ManyToOne
    @JoinColumn(name = "ordenId")
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "id")
    private Medicamentos medicamento;

    @Column(name = "precio_unidad", nullable = false)
    private BigDecimal precioUnidad;

    @Column(nullable = false)
    private int cantidad;

    // Constructor por defecto
    public OrdenDetalle() {}

    // Constructor con parámetros
    public OrdenDetalle(BigDecimal precioUnidad, int cantidad) {
        this.precioUnidad = precioUnidad;
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(BigDecimal precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Medicamentos getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamentos medicamento) {
        this.medicamento = medicamento;
    }
}
