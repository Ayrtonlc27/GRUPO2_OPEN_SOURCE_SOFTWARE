import { Component } from '@angular/core';
import { MedicamentoService } from '../../services/medicamento.service';
import { Router } from '@angular/router';
import { Medicamento } from '../../models/Medicamento';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-registrar-medicamento',
  templateUrl: './registrar-medicamento.component.html',
  styleUrls: ['./registrar-medicamento.component.scss'],
})
export class RegistrarMedicamentoComponent {
  medicamento: Medicamento = {
    nombre: '',
    categoria: '',
    codigo: '',
    nivelMinimo: 0,
    fechaExpiracion: new Date,
    cantidadDisponible: 0,
    precio: 0
  };

  submitted: boolean = false;

  constructor(private medicamentoService: MedicamentoService, private router: Router) {}

  guardarMedicamento() {
    this.submitted = true;

    if (this.esFormularioValido()) {
      this.medicamentoService.registrarMedicamento(this.medicamento).subscribe({
        next: () => {
          Swal.fire({
            position: "top-end",
            icon: "success",
            title: "Medicamento registrado con éxito",
            showConfirmButton: false,
            timer: 1500
          });
          
          this.router.navigate(['/lista']);
        },
        error: () => {
          Swal.fire({
            position: "top-end",
            icon: "error",
            title: "Error al registrar el medicamento",
            showConfirmButton: false,
            timer: 1500
          });
        }
      });
    }
  }

  esFormularioValido(): boolean {
    return !!(
      this.medicamento.nombre &&
      this.medicamento.categoria &&
      this.medicamento.codigo &&
      this.medicamento.nivelMinimo > 0 &&
      this.medicamento.fechaExpiracion &&
      this.medicamento.cantidadDisponible > 0 &&
      this.medicamento.precio > 0
    );
  }
}
