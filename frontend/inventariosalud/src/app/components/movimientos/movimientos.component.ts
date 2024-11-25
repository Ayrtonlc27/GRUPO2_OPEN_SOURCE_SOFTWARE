import { Component, OnInit } from '@angular/core';
import { Movimiento } from '../../models/Movimiento';
import { MovimientosService } from '../../services/movimientos.service';
import { MatDialog } from '@angular/material/dialog';
import { MovimientoFormDialogComponent } from '../../shared/forms/movimiento-form-dialog/movimiento-form-dialog.component';
import { ConfirmDialogComponent } from '../../shared/forms/confirm-dialog/confirm-dialog.component';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { CommonModule, NgIf } from '@angular/common';
import { DataSource } from '@angular/cdk/collections';
import { MatTableModule } from '@angular/material/table'; 


@Component({
  selector: 'app-movimientos',
  standalone: true,
  imports: [RouterModule,MatIconModule,MatProgressSpinner,NgIf,MatTableModule,CommonModule],
  templateUrl: './movimientos.component.html',
  styleUrl: './movimientos.component.css'
})
export class MovimientosComponent  implements OnInit {

  displayedColumns: string[] = [  
    'medicamento',   
    'tipoMovimiento',   
    'fecha',   
    'cantidad',   
    'usuario'  
  ]; 

  movimientos: Movimiento[] = [];  
  cargando: boolean = false;  
  error: string | null = null;  

  constructor( private movimientosService: MovimientosService,  
    private dialog: MatDialog  ) { 
    
  }  

  ngOnInit(): void {  
    
    this.cargarMovimientos();    
  }  

  cargarMovimientos(): void {  
    this.cargando = true;  
    this.movimientosService.listarMovimientos().subscribe({  
      next: (movimientos) => {  
        this.movimientos = movimientos;  
        this.cargando = false;  
      },  
      error: (err) => {  
        this.error = 'Error al cargar los movimientos';  
        this.cargando = false;  
        console.error(err);  
      }  
    });  
  }  

  abrirDialogoNuevoMovimiento(): void {  
    const dialogRef = this.dialog.open(MovimientoFormDialogComponent, {  
      width: '500px',  
      data: {} // Puedes pasar datos iniciales si es necesario  
    });  

    dialogRef.afterClosed().subscribe(resultado => {  
      if (resultado) {  
        this.movimientosService.crearMovimiento(resultado).subscribe({  
          next: () => {  
            this.cargarMovimientos();  
            // Mostrar mensaje de éxito  
          },  
          error: (err) => {  
            console.error('Error al crear movimiento', err);  
            // Mostrar mensaje de error  
          }  
        });  
      }  
    });  
  }  

  editarMovimiento(movimiento: Movimiento): void {  
    const dialogRef = this.dialog.open(MovimientoFormDialogComponent, {  
      width: '500px',  
      data: movimiento  
    });  

    dialogRef.afterClosed().subscribe(resultado => {  
      if (resultado) {  
        this.movimientosService.actualizarMovimiento(resultado.id, resultado).subscribe({  
          next: () => {  
            this.cargarMovimientos();  
            // Mostrar mensaje de éxito  
          },  
          error: (err) => {  
            console.error('Error al actualizar movimiento', err);  
            // Mostrar mensaje de error  
          }  
        });  
      }  
    });  
  }  

  eliminarMovimiento(id: number): void {  
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {  
      width: '350px',  
      data: {  
        titulo: 'Confirmar Eliminación',  
        mensaje: '¿Está seguro que desea eliminar este movimiento?'  
      }  
    });  

    dialogRef.afterClosed().subscribe(confirmacion => {  
      if (confirmacion) {  
        this.movimientosService.eliminarMovimiento(id).subscribe({  
          next: () => {  
            this.cargarMovimientos();  
            // Mostrar mensaje de éxito  
          },  
          error: (err) => {  
            console.error('Error al eliminar movimiento', err);  
            // Mostrar mensaje de error  
          }  
        });  
      }  
    });  
  }  


}  


