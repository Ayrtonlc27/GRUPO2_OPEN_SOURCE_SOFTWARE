import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Orden } from '../models/Orden';
import { OrdenDetalle } from '../models/Orden';


@Injectable({
  providedIn: 'root'
})
export class OrdenService {
  private baseUrl = 'http://localhost:8090/api/ordenes';

  constructor(private http: HttpClient) {}

  listarOrdenes(): Observable<Orden[]> {
    return this.http.get<Orden[]>(this.baseUrl);
  }

  verDetalle(id: number): Observable<OrdenDetalle[]> {
    return this.http.get<OrdenDetalle[]>(`${this.baseUrl}/verDetalle/${id}`);
  }
}
