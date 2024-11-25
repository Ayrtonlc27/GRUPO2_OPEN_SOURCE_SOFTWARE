export interface Alerta {  
    id?: number;  
    id_medicamento: string;  
    tipo_alerta: string;  
    mensaje: string;  
    fecha_generada: Date;  
    estado: 'Activo' | 'Desactivado';  
  }  