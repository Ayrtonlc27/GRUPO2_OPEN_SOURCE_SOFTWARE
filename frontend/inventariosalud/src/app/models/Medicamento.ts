export interface Medicamento {
    id?: number; // Opcional porque al registrar no hay ID
    nombre: string;
    categoria: string;
    codigo: string;
    nivelMinimo: number;
    fechaExpiracion: Date; // Usamos string para simplificar el manejo de fechas
    cantidadDisponible: number;
    precio: number;
  }
  