package com.inventario.productos.salud.negocio;

import com.inventario.productos.salud.entidades.Medicamentos;
import com.inventario.productos.salud.entidades.Orden;
import com.inventario.productos.salud.entidades.OrdenDetalle;
import com.inventario.productos.salud.repositorio.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenServicio {

    @Autowired
    private OrdenRepositorio ordenRepositorio;

    @Autowired
    private MedicamentoRepositorio medicamentoRepositorio;

    @Autowired
    private OrdenDetalleRepositorio ordenDetalleRepositorio;

    public List<OrdenProjection> listarOrdenes() {
        return ordenRepositorio.listarOrdenes();
    }

    public List<OrdenDetalle> listarDetalleOrden(Long ordenId) {
        return ordenDetalleRepositorio.listarDetalleOrden(ordenId);
    }

    @Transactional
    public void crearOrdenMedicamentos(){
        List<Medicamentos> listado_medicamentos = medicamentoRepositorio.findAll();
        int cantidad_por_defecto = 20;

        Orden orden = new Orden();
        String fechaOrdenStr = "2024-11-25";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(fechaOrdenStr, formatter);
        Date fechaOrden = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        String fechaRequeridaStr = "2024-12-05";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate2 = LocalDate.parse(fechaRequeridaStr, formatter2);
        Date fechaRequerida = Date.from(localDate2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        orden.setFechaOrden(fechaOrden);
        orden.setFechaRequerida(fechaRequerida);
        orden.setProveedor("FARMACIAS PERÚ");
        orden = ordenRepositorio.save(orden);

        for(Medicamentos medicamento : listado_medicamentos) {
            if(medicamento.getCantidadDisponible() <= medicamento.getNivelMinimo()) {
                OrdenDetalle ordenDetalle = new OrdenDetalle();
                ordenDetalle.setOrden(orden);
                ordenDetalle.setMedicamento(medicamento);
                ordenDetalle.setCantidad(cantidad_por_defecto);
                ordenDetalle.setPrecioUnidad(medicamento.getPrecio());
                ordenDetalleRepositorio.save(ordenDetalle);
            }
        }
        //return orden;
    }
}
