package com.inventario.productos.salud.entidades;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Medicamentos")
public class Medicamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String categoria;

    @Column(unique = true, nullable = false)
    private String codigo;

    @Column(name = "nivel_minimo", nullable = false)
    private int nivelMinimo;

    @Column(name = "fecha_expiracion")
    @Temporal(TemporalType.DATE)
    private Date fechaExpiracion;

    @Column(name = "cantidad_disponible", nullable = false)
    private int cantidadDisponible;

    @Column(nullable = false)
    private BigDecimal precio;

    // Constructor por defecto
    public Medicamentos() {}

    // Constructor con parámetros
    public Medicamentos(String nombre, String categoria, String codigo, int nivelMinimo, Date fechaExpiracion, int cantidadDisponible, BigDecimal precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.codigo = codigo;
        this.nivelMinimo = nivelMinimo;
        this.fechaExpiracion = fechaExpiracion;
        this.cantidadDisponible = cantidadDisponible;
        this.precio = precio;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getNivelMinimo() {
        return nivelMinimo;
    }

    public void setNivelMinimo(int nivelMinimo) {
        this.nivelMinimo = nivelMinimo;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

}
