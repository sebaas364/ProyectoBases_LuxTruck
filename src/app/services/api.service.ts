import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface LoginDTO {
  correo: string;
  contrasenia: string;
}

export interface TrabajadorDTO {
  idPersona: number;
  primerNombre: string;
  segundoNombre?: string;
  primerApellido: string;
  segundoApellido?: string;
  numeroDocumento: string;
  tipoDocumento: string;
  telefono: string;
  correo: string;
  salario: number;
  fechaIngreso: string;
  estadoTrabajadordto: {
    idEstadoTrabajador: number;
    nombre: string;
  };
}

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private readonly BASE_URL = 'http://localhost:8084';

  constructor(private http: HttpClient) {}

  // ──────────────────────────────────────────
  // AUTH
  // ──────────────────────────────────────────
  login(datos: LoginDTO): Observable<TrabajadorDTO> {
    return this.http.post<TrabajadorDTO>(`${this.BASE_URL}/auth/login`, datos);
  }

}
