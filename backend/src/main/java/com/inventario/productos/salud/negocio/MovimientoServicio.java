package com.inventario.productos.salud.negocio;

import com.inventario.productos.salud.entidades.Alertas;
import com.inventario.productos.salud.entidades.Medicamentos;
import com.inventario.productos.salud.entidades.Movimiento;
import com.inventario.productos.salud.repositorio.AlertasRepositorio;
import com.inventario.productos.salud.repositorio.MedicamentoRepositorio;
import com.inventario.productos.salud.repositorio.MovimientoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoServicio implements IMovimientoServicio {
    @Autowired
    private MovimientoRepositorio movimientoRepositorio;
    @Autowired
    private MedicamentoRepositorio medicamentoRepositorio;
    @Autowired
    private AlertasRepositorio alertasRepositorio;

    @Override
    public Movimiento crear(Movimiento movimiento) {
            System.out.println("movimiento: " + movimiento);
        if (movimiento.getTipo_movimiento().equals("ingreso")){
            Optional<Medicamentos> medicamento = medicamentoRepositorio.findById(movimiento.getId_medicamento());
           if (medicamento.isEmpty()){
               throw new RuntimeException("Medicamento no encontrado con ID: " + movimiento.getId_medicamento());
           }
           Medicamentos medicamentos = medicamento.get();
           int cantidadMedicamento = medicamentos.getCantidadDisponible();
           medicamentos.setCantidadDisponible(cantidadMedicamento+ movimiento.getCantidad());

           Optional<Alertas> alertaNivelBajoExistente = alertasRepositorio.obtenerPorIdMedicamentoAndTipoAlerta(movimiento.getId_medicamento());
           if (alertaNivelBajoExistente.isPresent()){
               Alertas alertaNivelBajo = alertaNivelBajoExistente.get();
               alertasRepositorio.delete(alertaNivelBajo);
           }
           return movimientoRepositorio.save(movimiento);

        }
       else {
           Optional<Medicamentos> medicamento = medicamentoRepositorio.findById(movimiento.getId_medicamento());
           if (medicamento.isEmpty()){
               throw new RuntimeException("Medicamento no encontrado con ID: " + movimiento.getId_medicamento());
           }
           Medicamentos medicamentos = medicamento.get();
           int cantidadMedicamento = medicamentos.getCantidadDisponible();
           if (cantidadMedicamento < movimiento.getCantidad()){
               throw new RuntimeException("No existe suficiente stock del medicamento");
           }
           if (cantidadMedicamento - movimiento.getCantidad() < medicamentos.getNivelMinimo())
           {
               Alertas alerta = new Alertas();
               alerta.setEstado(true);
               alerta.setId_medicamento(medicamentos.getId());
               alerta.setTipo_alerta("nivel_minimo");
               alerta.setMensaje("Alerta de nivel de stock bajo");
               alerta.setFecha_generada(new Date());
               alertasRepositorio.save(alerta);
           }
           medicamentos.setCantidadDisponible(cantidadMedicamento- movimiento.getCantidad());
           return movimientoRepositorio.save(movimiento);
        }


    }

    @Override
    public Long eliminar(Long id) {
        Movimiento movimiento = movimientoRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Movimiento no encontrado con ID: " + id));
        Medicamentos medicamentos = medicamentoRepositorio.findById(movimiento.getId_medicamento()).orElseThrow(() -> new RuntimeException("Medicamento no encontrado con ID: " + movimiento.getId_medicamento()));
        int movimientoCantidad = movimiento.getCantidad();
        if (movimiento.getTipo_movimiento().equals("ingreso")){
            int cantidadMedicamento = medicamentos.getCantidadDisponible();
            medicamentos.setCantidadDisponible(cantidadMedicamento- movimientoCantidad);
        }
        else {

            int cantidadMedicamento = medicamentos.getCantidadDisponible();
            medicamentos.setCantidadDisponible(cantidadMedicamento+ movimientoCantidad);
        }
        movimientoRepositorio.delete(movimiento);
        verificarAlerta(movimiento.getId_medicamento());

        return id;
    }

    private void verificarAlerta(long id_medicamento){
        Medicamentos medicamento = medicamentoRepositorio.findById(id_medicamento).orElseThrow(() -> new RuntimeException("Medicamento no encontrado con ID: " + id_medicamento));
        Optional<Alertas> alertas  = alertasRepositorio.obtenerPorIdMedicamentoAndTipoAlerta(id_medicamento);
        boolean existeAlerta = alertas.isPresent();
        boolean debeALertarse = medicamento.getCantidadDisponible() < medicamento.getNivelMinimo();

        if (debeALertarse && !existeAlerta){
            Alertas alerta = new Alertas();
            alerta.setEstado(true);
            alerta.setId_medicamento(medicamento.getId());
            alerta.setTipo_alerta("nivel_minimo");
            alerta.setMensaje("Alerta de nivel de stock bajo");
            alerta.setFecha_generada(new Date());
            alertasRepositorio.save(alerta);

        }
        else if (!debeALertarse && existeAlerta){
            Alertas alerta = alertas.get();
            alertasRepositorio.delete(alerta);
        }
    }


    public Movimiento actualizar(Movimiento movimiento, Long id) {
        Movimiento movimientoExistente = movimientoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado con ID: " + id));
        return manejarMovimiento(movimiento, movimientoExistente);
    }

    private Movimiento manejarMovimiento(Movimiento movimiento, Movimiento movimientoExistente) {
        Optional<Medicamentos> medicamentoOpt = medicamentoRepositorio.findById(movimiento.getId_medicamento());
        if (medicamentoOpt.isEmpty()) {
            throw new RuntimeException("Medicamento no encontrado con ID: " + movimiento.getId_medicamento());
        }

        Medicamentos medicamentos = medicamentoOpt.get();

        int cantidadActual = medicamentos.getCantidadDisponible();
        int cantidadMovExistente = (movimientoExistente != null) ? movimientoExistente.getCantidad() : 0;

        if (movimientoExistente != null) {
            // Temporalmente revertir la cantidad del movimiento existente
            cantidadActual = revertirMovimiento(medicamentos, movimientoExistente);
        }

        // Verificar y aplicar el nuevo movimiento
        actualizarStock(medicamentos, movimiento.getTipo_movimiento(), movimiento.getCantidad(), cantidadActual, movimientoExistente);
        movimiento.setId_movimiento(movimientoExistente.getId_movimiento());
        return movimientoRepositorio.save(movimiento);
    }
// revertimos los cambios, si el tipo de movimiento era ingreso restamos y si era retiro sumamos
    private int revertirMovimiento(Medicamentos medicamentos, Movimiento movimientoExistente) {
        int cantidadActual = medicamentos.getCantidadDisponible();
        if (movimientoExistente.getTipo_movimiento().equals("ingreso")) {
            cantidadActual -= movimientoExistente.getCantidad();
        } else {
            cantidadActual += movimientoExistente.getCantidad();
        }
        return cantidadActual;
    }

    private void actualizarStock(Medicamentos medicamentos, String tipoMovimiento, int cantidad, int cantidadActual, Movimiento movimientoExistente) {
        if (tipoMovimiento.equals("ingreso")) {
            medicamentos.setCantidadDisponible(cantidadActual + cantidad);
        } else if (tipoMovimiento.equals("salida")) {
            if (cantidadActual < cantidad) {
                throw new RuntimeException("No existe suficiente stock del medicamento");
            }
            if (cantidadActual - cantidad < medicamentos.getNivelMinimo()) {
                Alertas alerta = new Alertas();
                alerta.setEstado(true);
                alerta.setId_medicamento(medicamentos.getId());
                alerta.setTipo_alerta("nivel_minimo");
                alerta.setFecha_generada(new Date());
                alerta.setMensaje("Alerta de nivel de stock bajo");
                alertasRepositorio.save(alerta);
            }
            medicamentos.setCantidadDisponible(cantidadActual - cantidad);
        }

        medicamentoRepositorio.save(medicamentos);
    }

    public List<Movimiento> listar() {
        List<Movimiento>  listaMovimientos = movimientoRepositorio.findAll();
        listaMovimientos.forEach(movimiento -> {
            Medicamentos medicamento = medicamentoRepositorio.findById(movimiento.getId_medicamento()).orElse(null);
            if (medicamento != null) {
                movimiento.setNombre_medicamento(medicamento.getNombre());
            }
            // pendiente agregar usuario
        });
        return listaMovimientos;
    }
}
