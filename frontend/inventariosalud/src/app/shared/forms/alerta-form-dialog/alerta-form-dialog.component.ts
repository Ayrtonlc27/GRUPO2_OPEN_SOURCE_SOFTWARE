import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogActions, MatDialogContent, MatDialogRef } from '@angular/material/dialog';
import { Alerta } from '../../../models/Alertas';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-alerta-form-dialog',
  standalone: true,
  imports: [ReactiveFormsModule,MatSelectModule,MatDialogContent,MatDialogActions],
  templateUrl: './alerta-form-dialog.component.html',
  styleUrl: './alerta-form-dialog.component.css'
})
export class AlertaFormDialogComponent  {

  formulario:FormGroup

  constructor(  
    private fb: FormBuilder,  
    public dialogRef: MatDialogRef<AlertaFormDialogComponent>,  
    @Inject(MAT_DIALOG_DATA) public datos: Alerta  
  ) {
    this.formulario = this.fb.group({  
      id: [this.datos.id],  
      medicamento: [this.datos.id_medicamento, Validators.required],  
      tipoAlerta: [this.datos.tipo_alerta, Validators.required],  
      mensaje: [this.datos.mensaje, Validators.required],  
      fechaGenerada: [this.datos.fecha_generada || new Date()],  
      estado: [this.datos.estado || 'Activo']  
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
