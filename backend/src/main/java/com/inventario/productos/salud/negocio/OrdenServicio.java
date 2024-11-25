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
    public Orden crearOrdenMedicamentos(Orden orden_request){
        List<Medicamentos> listado_medicamentos = medicamentoRepositorio.findAll();
        int cantidad_por_defecto = 10;

        Orden orden = new Orden();
        LocalDate localDate = LocalDate.of(2020, 2, 20);
        orden.setFechaOrden(orden_request.getFechaOrden());
        orden.setFechaRequerida(orden_request.getFechaRequerida());
        orden.setProveedor(orden_request.getProveedor());
        orden = ordenRepositorio.save(orden);

        for(Medicamentos medicamento : listado_medicamentos) {
            if(medicamento.getCantidadDisponible() < 5) {
                OrdenDetalle ordenDetalle = new OrdenDetalle();
                ordenDetalle.setOrden(orden);
                ordenDetalle.setMedicamento(medicamento);
                ordenDetalle.setCantidad(cantidad_por_defecto);
                ordenDetalle.setPrecioUnidad(medicamento.getPrecio());
                ordenDetalleRepositorio.save(ordenDetalle);
            }
        }
        return orden;
    }
}
