package com.inventario.productos.salud.negocio;

import com.inventario.productos.salud.entidades.Alertas;
import com.inventario.productos.salud.entidades.Medicamentos;
import com.inventario.productos.salud.repositorio.AlertasRepositorio;
import com.inventario.productos.salud.repositorio.MedicamentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class AlertaServicio  implements  IAlertaServicio{

    @Autowired
    private AlertasRepositorio alertasRepositorio;
    @Autowired
    private MedicamentoRepositorio medicamentoRepositorio;

    @Override
    public void registrar(Alertas alertas) {
        alertasRepositorio.save(alertas);
    }

    @Override
    public void eliminar(Long id) {
    Alertas alerta = alertasRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Alerta no encontrado con ID: " + id)
        );
    alerta.setEstado(false);
    }

    @Override
    public List<Alertas> listar() {

        List<Alertas> listaAlertas =  alertasRepositorio.findAll();
        listaAlertas.forEach(alerta -> {
            Medicamentos medicamento = medicamentoRepositorio.findById(alerta.getId_medicamento()).orElse(null);
            if (medicamento != null) {
                alerta.setNombre_medicamento(medicamento.getNombre());
            }
        });

        return listaAlertas;

    }

    @Override
    public Alertas obtenerPorId(Long id) {
        return alertasRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Alerta no encontrado con ID: " + id)
        );
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public void verificarFechaDeExpiracion() {
        Date hoy = new Date();
        LocalDate hoyLocal = hoy.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        List<Medicamentos> listaMedicamentos = medicamentoRepositorio.findAll();
        for (Medicamentos medicamento : listaMedicamentos) {
            Date fechaExpiracion = medicamento.getFechaExpiracion();
            LocalDate fechaExpiracionLocal = fechaExpiracion.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            long diasFaltantes = ChronoUnit.DAYS.between(hoyLocal, fechaExpiracionLocal);
            if (diasFaltantes <= 10){
                Alertas alerta = new Alertas();
                alerta.setEstado(true);
                alerta.setId_medicamento(medicamento.getId());
                alerta.setTipo_alerta("vencimiento");
                alerta.setMensaje("Alerta de fecha de expiracion");
                alertasRepositorio.save(alerta);
            }

        }
    }


}
