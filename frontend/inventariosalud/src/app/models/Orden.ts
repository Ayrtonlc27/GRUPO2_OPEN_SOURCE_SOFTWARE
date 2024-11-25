import { DecimalPipe } from "@angular/common";

export interface Orden {
    fechaOrden: Date;
    fechaRequerida: Date;
    proveedor: string;
    nombre: string;
    cantidad: number;
    precio: DecimalPipe;
  }
  
  export interface OrdenDetalle {
    fechaRequerida: Date;
    nombre: string;
    precioUnidad: string;
    cantidad: number;
    ordenId: number;
  }
  