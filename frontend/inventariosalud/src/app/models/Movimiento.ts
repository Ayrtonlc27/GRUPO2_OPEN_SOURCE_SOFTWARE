export interface Movimiento {  
    id_movimiento: Number;
    id_medicamento:Number;
    tipo_movimiento: 'ingreso' | 'salida';  
    fecha_movimiento: Date;  
    cantidad: number;  
    id_usuario: string;  
    nombre_medicamento:string;
    nombre_usuario:string;
  }  