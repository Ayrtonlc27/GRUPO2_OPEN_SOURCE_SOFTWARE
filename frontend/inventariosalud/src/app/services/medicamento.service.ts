import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Medicamento } from '../models/Medicamento';


@Injectable({
  providedIn: 'root'
})
export class MedicamentoService {
  private baseUrl = 'http://localhost:8082/api/medicamentos';

  constructor(private http: HttpClient) {}

  listarMedicamentos(): Observable<Medicamento[]> {
    return this.http.get<Medicamento[]>(this.baseUrl);
  }

  registrarMedicamento(medicamento: Medicamento): Observable<Medicamento> {
    return this.http.post<Medicamento>(this.baseUrl, medicamento);
  }

  actualizarMedicamento(id: number, medicamento: Medicamento): Observable<Medicamento> {
    return this.http.put<Medicamento>(`${this.baseUrl}/${id}`, medicamento);
  }

  eliminarMedicamento(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
  
  //HU0005
  buscarMedicamentos(filtro: string, valor: string): Observable<Medicamento[]> {
    if(valor.length > 0){
      if(filtro == "codigo"){
        return this.http.get<Medicamento[]>(`${this.baseUrl}/busquedaCodigo/${valor}`);
      }else if(filtro == "nombre"){
        return this.http.get<Medicamento[]>(`${this.baseUrl}/busquedaDescripcion/${valor}`);
      }
    }
      return this.http.get<Medicamento[]>(this.baseUrl);
  }
}
