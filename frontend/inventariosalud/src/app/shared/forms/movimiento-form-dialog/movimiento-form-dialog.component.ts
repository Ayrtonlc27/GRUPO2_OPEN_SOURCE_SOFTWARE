import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogActions, MatDialogContent, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { Movimiento } from '../../../models/Movimiento';
import { MatFormFieldModule } from '@angular/material/form-field';  
import { MatInputModule } from '@angular/material/input';  
import { MatSelectModule } from '@angular/material/select';
import { MovimientosService } from '../../../services/movimientos.service';
import { Medicamento } from '../../../models/Medicamento';
import { MedicamentoService } from '../../../services/medicamento.service';
import { NgForOf } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-movimiento-form-dialog',
  standalone: true,
  imports: [MatDialogContent,MatDialogActions,MatFormFieldModule,MatInputModule,MatSelectModule,ReactiveFormsModule,NgForOf,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatDialogModule
  ],
  templateUrl: './movimiento-form-dialog.component.html',
  styleUrl: './movimiento-form-dialog.component.css'
})



export class MovimientoFormDialogComponent implements OnInit {
  formulario: FormGroup;  
  medicamentos: Medicamento[] = [];
   

  constructor(private fb: FormBuilder,  
    public dialogRef: MatDialogRef<MovimientoFormDialogComponent>,  
    @Inject(MAT_DIALOG_DATA) public datos: Movimiento, private movimientoService:MovimientosService, private medicamentoservice: MedicamentoService  ){


      this.formulario = this.fb.group({  
        id:[this.datos.id_movimiento],
        tipo_movimiento:[this.datos.tipo_movimiento],
        cantidad:[this.datos.cantidad],
        fecha_movimiento:new Date(),
        id_usuario:[this.datos.id_usuario || 2 ],
        id_medicamento:[this.datos.id_medicamento]
      });  
      

    }

    ngOnInit(): void {
      this.medicamentoservice.listarMedicamentos().subscribe(medicamentos=> this.medicamentos = medicamentos )
    }

    guardar() {  
      if (this.formulario.valid) {
      
      //  this.movimientoService.crearMovimiento(this.datos).subscribe(

      //  )
         this.dialogRef.close(this.formulario.value);  
      }  
    }  
  
    cancelar() {  
      this.dialogRef.close();  
    }  

}
