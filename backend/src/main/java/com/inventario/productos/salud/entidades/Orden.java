package com.inventario.productos.salud.entidades;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Orden")
public class Orden {

    @Id
    @Column(name = "ordenId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordenId;

    @Column(name = "fecha_orden")
    @Temporal(TemporalType.DATE)
    private Date fechaOrden;

    @Column(name = "fecha_requerida")
    @Temporal(TemporalType.DATE)
    private Date fechaRequerida;

    private String proveedor;

    @OneToMany(mappedBy = "orden")
    private Set<OrdenDetalle> ordenDetalles = new HashSet<>();

    // Constructor por defecto
    public Orden() {}

    // Constructor con parámetros
    public Orden(Date fechaOrden, Date fechaRequerida, String proveedor) {
        this.fechaOrden = fechaOrden;
        this.fechaRequerida = fechaRequerida;
        this.proveedor = proveedor;
    }

    public Long getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Long ordenId) {
        this.ordenId = ordenId;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public Date getFechaRequerida() {
        return fechaRequerida;
    }

    public void setFechaRequerida(Date fechaRequerida) {
        this.fechaRequerida = fechaRequerida;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Set<OrdenDetalle> getOrdenDetalles(){
        return ordenDetalles;
    }

    public void serOrdenDetalle(Set<OrdenDetalle> ordenDetalles){
        this.ordenDetalles = ordenDetalles;
    }
}
