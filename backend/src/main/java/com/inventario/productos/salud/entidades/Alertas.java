package com.inventario.productos.salud.entidades;
import lombok.Data;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Data
@Table(name = "alertas")
public class Alertas {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_alerta;
@Column(nullable = false)
    private Long id_medicamento;
@Column
    private String tipo_alerta;
@Column
    private String mensaje;
@Column(nullable = false)
    private Date fecha_generada;
@Column
    private boolean estado;
@Transient
private String nombre_medicamento;

    public Alertas(Long id_alerta, Long id_medicamento, String tipo_alerta, String mensaje, Date fecha_generada, boolean estado) {
        this.id_alerta = id_alerta;
        this.id_medicamento = id_medicamento;
        this.tipo_alerta = tipo_alerta;
        this.mensaje = mensaje;
        this.fecha_generada = fecha_generada;
        this.estado = estado;
    }

    public Alertas() {

    }
}
