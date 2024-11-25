import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogActions, MatDialogContent, MatDialogRef } from '@angular/material/dialog';
import { Movimiento } from '../../../models/Movimiento';
import { MatFormFieldModule } from '@angular/material/form-field';  
import { MatInputModule } from '@angular/material/input';  
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-movimiento-form-dialog',
  standalone: true,
  imports: [MatDialogContent,MatDialogActions,MatFormFieldModule,MatInputModule,MatSelectModule,ReactiveFormsModule],
  templateUrl: './movimiento-form-dialog.component.html',
  styleUrl: './movimiento-form-dialog.component.css'
})



export class MovimientoFormDialogComponent {
  formulario: FormGroup;  

  constructor(private fb: FormBuilder,  
    public dialogRef: MatDialogRef<MovimientoFormDialogComponent>,  
    @Inject(MAT_DIALOG_DATA) public datos: Movimiento  ){


      this.formulario = this.fb.group({  
        id:[this.datos.id_movimiento],
        tipo_movimiento:[this.datos.tipo_movimiento],
        cantidad:[this.datos.cantidad],
        fecha:[this.datos.fecha_movimiento],
        id_usuario:[this.datos.id_usuario],
        id_medicamento:[this.datos.id_medicamento]
      });  

    }

    guardar() {  
      if (this.formulario.valid) {  
        this.dialogRef.close(this.formulario.value);  
      }  
    }  
  
    cancelar() {  
      this.dialogRef.close();  
    }  

}
