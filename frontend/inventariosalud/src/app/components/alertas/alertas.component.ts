import { Component, OnInit } from '@angular/core';
import { Alerta } from '../../models/Alertas';
import { AlertasService } from '../../services/alertas.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../../shared/forms/confirm-dialog/confirm-dialog.component';
import { AlertaFormDialogComponent } from '../../shared/forms/alerta-form-dialog/alerta-form-dialog.component';
import { MatIcon } from '@angular/material/icon';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { CommonModule, NgIf } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatChipsModule } from '@angular/material/chips';
import { MatMenu, MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-alertas',
  standalone: true,
  imports: [
    MatProgressSpinner,
    NgIf,
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatChipsModule,
    MatMenuModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatDialogModule,
  ],
  templateUrl: './alertas.component.html',
  styleUrl: './alertas.component.css',
})
export class AlertasComponent implements OnInit {
  displayedColumns: string[] = [
    'id',
    'medicamento',
    'tipoAlerta',
    'mensaje',
    'fechaGenerada',
    'estado',
  ];

  alertas: Alerta[] = [];
  cargando: boolean = false;
  error: string | null = null;

  constructor(
    private alertasService: AlertasService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.cargarAlertas();
  }

  cargarAlertas(): void {
    this.cargando = true;
    this.alertasService.listarAlertas().subscribe({
      next: (alertas) => {
        this.alertas = alertas;
        this.cargando = false;
      },
      error: (err) => {
        this.error = 'Error al cargar las alertas';
        this.cargando = false;
        console.error(err);
      },
    });
  }

  abrirDialogoNuevaAlerta(): void {
    const dialogRef = this.dialog.open(AlertaFormDialogComponent, {
      width: '500px',
      data: {},
    });

    dialogRef.afterClosed().subscribe((resultado) => {
      if (resultado) {
        this.alertasService.crearAlerta(resultado).subscribe({
          next: () => {
            this.cargarAlertas();
          },
          error: (err) => {
            console.error('Error al crear alerta', err);
          },
        });
      }
    });
  }

  editarAlerta(alerta: Alerta): void {
    const dialogRef = this.dialog.open(AlertaFormDialogComponent, {
      width: '500px',
      data: alerta,
    });

    dialogRef.afterClosed().subscribe((resultado) => {
      if (resultado) {
        this.alertasService
          .actualizarAlerta(resultado.id, resultado)
          .subscribe({
            next: () => {
              this.cargarAlertas();
            },
            error: (err) => {
              console.error('Error al actualizar alerta', err);
            },
          });
      }
    });
  }

  cambiarEstadoAlerta(id: number, nuevoEstado: string): void {
    this.alertasService.cambiarEstadoAlerta(id, nuevoEstado).subscribe({
      next: () => {
        this.cargarAlertas();
      },
      error: (err) => {
        console.error('Error al cambiar estado', err);
      },
    });
  }

  eliminarAlerta(id: number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '350px',
      data: {
        titulo: 'Confirmar Eliminación',
        mensaje: '¿Está seguro que desea eliminar esta alerta?',
      },
    });

    dialogRef.afterClosed().subscribe((confirmacion) => {
      if (confirmacion) {
        this.alertasService.eliminarAlerta(id).subscribe({
          next: () => {
            this.cargarAlertas();
          },
          error: (err) => {
            console.error('Error al eliminar alerta', err);
          },
        });
      }
    });
  }

  getColorEstado(estado: string): string {
    switch (estado) {
      case 'Activo':
        return 'warn';
      case 'Pendiente':
        return 'accent';
      case 'Resuelto':
        return 'primary';
      default:
        return '';
    }
  }
}
