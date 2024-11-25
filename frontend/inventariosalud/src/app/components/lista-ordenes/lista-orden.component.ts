import { Component, OnInit } from '@angular/core';
import { OrdenService,  } from '../../services/orden.service';
import { Orden } from '../../models/Orden';
import { OrdenDetalle } from '../../models/Orden';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-lista-orden',
  templateUrl: './lista-orden.component.html'
})
export class ListaOrdenComponent implements OnInit {
  ordenes: Orden[] = [];
  ordendetalle:  OrdenDetalle[] = [];
 
  mostrarDialogoEdicion: boolean = false; // Controla la visibilidad del diálogo de edición

  constructor(private OrdenService: OrdenService) {}

  ngOnInit() {
    this.cargarOrdenes();
  }

  cargarOrdenes() {
    this.OrdenService.listarOrdenes().subscribe(data => {
      this.ordenes = data;
    });
  }

  cancelarEdicion() {
    this.mostrarDialogoEdicion = false;
  }  

  verDetalle(id:number) {
    this.mostrarDialogoEdicion = true; // Muestra el diálogo de edición
    this.OrdenService.verDetalle(id).subscribe(data => {
      this.ordendetalle = data;
      console.log(data);
    });
  }

}
