package com.inventario.productos.salud.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_movimiento;
    @Column
    private Long id_medicamento;
    @Column
    private String tipo_movimiento;
    @Column
    private Date fecha_movimiento;
    @Column
    private Long id_usuario;
    @Column
    private int cantidad;
    @Transient
    private String nombre_medicamento;
    @Transient
    private String nombre_usuario;

    public Movimiento(Long id_movimiento, Long id_medicamento, String tipo_movimiento, Date fecha_movimiento, Long id_usuario, int cantidad) {
        this.id_movimiento = id_movimiento;
        this.id_medicamento = id_medicamento;
        this.tipo_movimiento = tipo_movimiento;
        this.fecha_movimiento = fecha_movimiento;
        this.id_usuario = id_usuario;
        this.cantidad = cantidad;
    }
    public Movimiento() {
    }
}
