import { Injectable } from '@angular/core';
import { Alerta } from '../models/Alertas';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AlertasService {
  private apiUrl = 'http://localhost:8082/api/alertas';  

  constructor(private http: HttpClient) { }


// Listar todas las alertas  
listarAlertas(): Observable<Alerta[]> {  
  return this.http.get<Alerta[]>(`${this.apiUrl}/listar`);  
}  

// Obtener alerta por ID  
obtenerAlerta(id: number): Observable<Alerta> {  
  return this.http.get<Alerta>(`${this.apiUrl}/${id}`);  
}  

// Crear nueva alerta  
crearAlerta(alerta: Alerta): Observable<Alerta> {  
  return this.http.post<Alerta>(this.apiUrl, alerta);  
}  

// Actualizar alerta  
actualizarAlerta(id: number, alerta: Alerta): Observable<Alerta> {  
  return this.http.put<Alerta>(`${this.apiUrl}/${id}`, alerta);  
}  

// Eliminar alerta  
eliminarAlerta(id: number): Observable<void> {  
  return this.http.delete<void>(`${this.apiUrl}/${id}`);  
}  

// Cambiar estado de la alerta  
cambiarEstadoAlerta(id: number, estado: string): Observable<Alerta> {  
  return this.http.patch<Alerta>(`${this.apiUrl}/${id}/estado`, { estado });  
}  
}  

