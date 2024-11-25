package com.inventario.productos.salud;

import com.inventario.productos.salud.entidades.Alertas;
import com.inventario.productos.salud.entidades.Medicamentos;
import com.inventario.productos.salud.entidades.Movimiento;
import com.inventario.productos.salud.negocio.AlertaServicio;
import com.inventario.productos.salud.repositorio.AlertasRepositorio;
import com.inventario.productos.salud.repositorio.MedicamentoRepositorio;
import com.inventario.productos.salud.repositorio.MovimientoRepositorio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SaludApplicationTests {

    @Autowired
    private AlertasRepositorio alertasRepositorio;

	@Test
	void contextLoads() {
	}

	@Autowired
	private MedicamentoRepositorio medicamentoRepositorio;
	@Autowired
	private MovimientoRepositorio movimientoRepositorio;
	@Autowired
	private AlertaServicio alertaServicio;

	@Test
	public void testGuardarMedicamento() {
		// Crear un nuevo medicamento
		Medicamentos medicamento = new Medicamentos();
		medicamento.setNombre("Paracetamol");
		medicamento.setCategoria("Analgesico");
		medicamento.setCodigo("PARA123");
		medicamento.setNivelMinimo(10);
		medicamento.setFechaExpiracion(new Date());
		medicamento.setCantidadDisponible(100);
		medicamento.setPrecio(new BigDecimal("5.50"));

		// Guardar en la base de datos
		Medicamentos guardado = medicamentoRepositorio.save(medicamento);

		// Validar que se guardó correctamente
		assertThat(guardado.getId()).isNotNull();
		assertThat(guardado.getNombre()).isEqualTo("Paracetamol");
	}

	@Test
	public void testListarMedicamentos() {
		// Crear y guardar medicamentos
		Medicamentos medicamento1 = new Medicamentos("Ibuprofeno", "Antiinflamatorio", "IBU123", 5, new Date(), 50, new BigDecimal("7.80"));
		Medicamentos medicamento2 = new Medicamentos("Aspirina", "Analgesico", "ASP456", 8, new Date(), 30, new BigDecimal("4.20"));
		medicamentoRepositorio.save(medicamento1);
		medicamentoRepositorio.save(medicamento2);

		// Obtener la lista de medicamentos
		List<Medicamentos> medicamentos = medicamentoRepositorio.findAll();

		// Validar la lista
		assertThat(medicamentos).isNotEmpty();
		assertThat(medicamentos.size()).isGreaterThanOrEqualTo(2);
	}

	@Test
	public void testActualizarMedicamento() {
		// Crear y guardar un medicamento
		Medicamentos medicamento = new Medicamentos("Amoxicilina", "Antibiótico", "AMOX789", 20, new Date(), 200, new BigDecimal("12.00"));
		Medicamentos guardado = medicamentoRepositorio.save(medicamento);

		// Actualizar el medicamento
		guardado.setNombre("Amoxicilina 500mg");
		guardado.setCantidadDisponible(180);
		Medicamentos actualizado = medicamentoRepositorio.save(guardado);

		// Validar la actualización
		assertThat(actualizado.getNombre()).isEqualTo("Amoxicilina 500mg");
		assertThat(actualizado.getCantidadDisponible()).isEqualTo(180);
	}

	@Test
	public void testEliminarMedicamento() {
		// Crear y guardar un medicamento
		Medicamentos medicamento = new Medicamentos("Cefalexina", "Antibiótico", "CEF123", 15, new Date(), 80, new BigDecimal("10.00"));
		Medicamentos guardado = medicamentoRepositorio.save(medicamento);

		// Eliminar el medicamento
		medicamentoRepositorio.delete(guardado);

		// Validar que se eliminó correctamente
		assertThat(medicamentoRepositorio.findById(guardado.getId())).isEmpty();
	}
@Test
	public void testGuardarMovimiento(){
		Movimiento movimiento = new Movimiento();
		movimiento.setTipo_movimiento("ingreso");
		movimiento.setCantidad(10);
		movimiento.setFecha_movimiento(new Date());
		movimiento.setId_medicamento(7l);
		movimiento.setId_usuario(2L);
		movimientoRepositorio.save(movimiento);


	}
	@Test
	public void testListarMovimientos(){

		List<Movimiento> listaMovimientos = movimientoRepositorio.findAll();
		for (Movimiento movimiento : listaMovimientos) {
			System.out.println(movimiento.getId_medicamento());
			System.out.println(movimiento.getId_usuario());
			System.out.println(movimiento.getTipo_movimiento());
			System.out.println(movimiento.getCantidad());
			System.out.println(movimiento.getFecha_movimiento());
		}
		System.out.println("---------------------------------------");


	}

@Test
	public void testGuardarAlerta(){
	Alertas alerta = new Alertas();
	alerta.setMensaje("Medicamento por vencer");
	alerta.setTipo_alerta("vencimiento");
	alerta.setId_medicamento(7L);
	alerta.setFecha_generada(new Date());
	alertasRepositorio.save(alerta);

}
@Test
	public void testListarAlertas(){
		List<Alertas> listaAlertas = alertasRepositorio.findAll();
		for (Alertas alerta : listaAlertas) {
			System.out.println(alerta);
		}
}

}
