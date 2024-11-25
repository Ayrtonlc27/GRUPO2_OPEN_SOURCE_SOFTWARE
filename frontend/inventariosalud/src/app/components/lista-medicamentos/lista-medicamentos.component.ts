import { Component, OnInit } from '@angular/core';
import { MedicamentoService,  } from '../../services/medicamento.service';
import { Medicamento } from '../../models/Medicamento';
import Swal from 'sweetalert2';
import { jsPDF } from 'jspdf';
import 'jspdf-autotable';

@Component({
  selector: 'app-lista-medicamentos',
  templateUrl: './lista-medicamentos.component.html'
})
export class ListaMedicamentosComponent implements OnInit {

  medicamentos: Medicamento[] = [];
  medicamentoSeleccionado:  Medicamento = {
    nombre: '',
    categoria: '',
    codigo: '',
    nivelMinimo: 0,
    fechaExpiracion: new Date,
    cantidadDisponible: 0,
    precio: 0
  };
  mostrarDialogoEdicion: boolean = false; // Controla la visibilidad del diálogo de edición

  constructor(private medicamentoService: MedicamentoService) {}

  ngOnInit() {
    this.cargarMedicamentos();
  }

  cargarMedicamentos() {
    this.medicamentoService.listarMedicamentos().subscribe(data => {
      this.medicamentos = data;
    });
  }

  editarMedicamento(medicamento: Medicamento) {
    this.medicamentoSeleccionado = { ...medicamento }; // Crea una copia del medicamento seleccionado
    this.medicamentoSeleccionado.fechaExpiracion = new Date(medicamento.fechaExpiracion);
    this.mostrarDialogoEdicion = true; // Muestra el diálogo de edición
  }

  eliminarMedicamento(id: number) {
    this.medicamentoService.eliminarMedicamento(id).subscribe(() => {
      this.cargarMedicamentos();
    });
  }


  guardarCambios() {
    if (this.medicamentoSeleccionado) {
      this.medicamentoService.actualizarMedicamento(this.medicamentoSeleccionado.id!, this.medicamentoSeleccionado)
        .subscribe({
          next: () => {
            this.mostrarDialogoEdicion = false;
            this.cargarMedicamentos();
            Swal.fire({
              position: "top-end",
              icon: "success",
              title: "Medicamento actualizado con éxito.",
              showConfirmButton: false,
              timer: 1500
            });
          },
          error: () => {

            Swal.fire({
              position: "top-end",
              icon: "error",
              title: "Error al actualizar el medicamento.",
              showConfirmButton: false,
              timer: 1500
            });

          }
        });
    }
  }

  cancelarEdicion() {
    this.mostrarDialogoEdicion = false;
    this.medicamentoSeleccionado  = {
      nombre: '',
      categoria: '',
      codigo: '',
      nivelMinimo: 0,
      fechaExpiracion: new Date,
      cantidadDisponible: 0,
      precio: 0
    };; // Limpia el medicamento seleccionado
  }

  exportarPDF() {
    const doc = new jsPDF();

    // Agregar título
    doc.text('Lista de Medicamentos', 10, 10);

    // Generar datos para la tabla
    const datosTabla = this.medicamentos.map(m => [
      m.nombre,
      m.categoria,
      m.codigo,
      m.nivelMinimo,
      m.fechaExpiracion, // Formato de fecha
      m.cantidadDisponible,
      m.precio.toFixed(2)
    ]);

    // Crear tabla
    (doc as any).autoTable({
      head: [['Nombre', 'Categoría', 'Código', 'Nivel Mínimo', 'Fecha Expiración', 'Cantidad', 'Precio']],
      body: datosTabla,
      startY: 20
    });

    // Descargar el archivo
    doc.save('lista-medicamentos.pdf');
  }
  
}
