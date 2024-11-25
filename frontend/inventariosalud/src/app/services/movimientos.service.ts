import { Injectable } from '@angular/core';
 
import { HttpClient } from '@angular/common/http';  
import { Observable } from 'rxjs';  
import { Movimiento } from '../models/Movimiento';

@Injectable({
  providedIn: 'root'
})
export class MovimientosService {
  private apiUrl = 'http://localhost:8082/api/movimientos';  

  constructor(private http: HttpClient) {}  
   // Listar movimientos  
   listarMovimientos(): Observable<Movimiento[]> {  
    return this.http.get<Movimiento[]>(`${this.apiUrl}/listar`);  
  }  

  // Crear movimiento  
  crearMovimiento(movimiento: Movimiento): Observable<Movimiento> {  
    return this.http.post<Movimiento>(this.apiUrl, movimiento);  
  }  

  // Obtener movimiento por ID  
  obtenerMovimiento(id: number): Observable<Movimiento> {  
    return this.http.get<Movimiento>(`${this.apiUrl}/${id}`);  
  }  

  // Actualizar movimiento  
  actualizarMovimiento(id: number, movimiento: Movimiento): Observable<Movimiento> {  
    return this.http.put<Movimiento>(`${this.apiUrl}/${id}`, movimiento);  
  }  

  // Eliminar movimiento  
  eliminarMovimiento(id: number): Observable<void> {  
    return this.http.delete<void>(`${this.apiUrl}/${id}`);  
  }  

}
