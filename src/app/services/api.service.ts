import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// ──────────────────────────────────────────
// INTERFACES
// ──────────────────────────────────────────

export interface LoginDTO {
  correo: string;
  contrasenia: string;
}

export interface EstadoTrabajadorDTO {
  idEstadoTrabajador: number;
  estado: string;
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
  contrasenia?: string;
  estadoTrabajadordto: EstadoTrabajadorDTO;
}

export interface AdministrativoDTO extends TrabajadorDTO {}

export interface OperarioDTO extends TrabajadorDTO {
  desempenio: number;
}

export interface VendedorDTO extends TrabajadorDTO {
  comision: number;
}

export interface CrearAdministrativoPayload {
  numeroDocumento: string;
  tipoDocumento: string;
  primerNombre: string;
  segundoNombre?: string;
  primerApellido: string;
  segundoApellido?: string;
  telefono: string;
  correo: string;
  salario: number;
  fechaIngreso: string;
  contrasenia: string;
  estadoTrabajadordto: EstadoTrabajadorDTO;
}

export interface CrearOperarioPayload extends CrearAdministrativoPayload {
  desempenio: number;
}

export interface CrearVendedorPayload extends CrearAdministrativoPayload {
  comision: number;
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

  // ──────────────────────────────────────────
  // ADMINISTRATIVOS
  // ──────────────────────────────────────────
  getAdministrativos(): Observable<AdministrativoDTO[]> {
    return this.http.get<AdministrativoDTO[]>(`${this.BASE_URL}/administrativo/getall`);
  }

  crearAdministrativo(datos: CrearAdministrativoPayload): Observable<string> {
    return this.http.post(`${this.BASE_URL}/administrativo/createjson`, datos, { responseType: 'text' });
  }

  editarAdministrativo(id: number, datos: Partial<AdministrativoDTO>): Observable<string> {
    return this.http.put(`${this.BASE_URL}/administrativo/update/${id}`, datos, { responseType: 'text' });
  }

  cambiarEstadoAdministrativo(idPersona: number, idEstado: number): Observable<string> {
    return this.http.post(`${this.BASE_URL}/administrativo/addestado?idPersona=${idPersona}&idEstado=${idEstado}`, null, { responseType: 'text' });
  }

  // ──────────────────────────────────────────
  // OPERARIOS
  // ──────────────────────────────────────────
  getOperarios(): Observable<OperarioDTO[]> {
    return this.http.get<OperarioDTO[]>(`${this.BASE_URL}/operario/getall`);
  }

  crearOperario(datos: CrearOperarioPayload): Observable<string> {
    return this.http.post(`${this.BASE_URL}/operario/createjson`, datos, { responseType: 'text' });
  }

  editarOperario(id: number, datos: Partial<OperarioDTO>): Observable<string> {
    return this.http.put(`${this.BASE_URL}/operario/update/${id}`, datos, { responseType: 'text' });
  }

  cambiarEstadoOperario(idPersona: number, idEstado: number): Observable<string> {
    return this.http.post(`${this.BASE_URL}/operario/addestado?idPersona=${idPersona}&idEstado=${idEstado}`, null, { responseType: 'text' });
  }

  // ──────────────────────────────────────────
  // VENDEDORES
  // ──────────────────────────────────────────
  getVendedores(): Observable<VendedorDTO[]> {
    return this.http.get<VendedorDTO[]>(`${this.BASE_URL}/vendedor/getall`);
  }

  crearVendedor(datos: CrearVendedorPayload): Observable<string> {
    return this.http.post(`${this.BASE_URL}/vendedor/createjson`, datos, { responseType: 'text' });
  }

  editarVendedor(id: number, datos: Partial<VendedorDTO>): Observable<string> {
    return this.http.put(`${this.BASE_URL}/vendedor/update/${id}`, datos, { responseType: 'text' });
  }

  cambiarEstadoVendedor(idPersona: number, idEstado: number): Observable<string> {
    return this.http.post(`${this.BASE_URL}/vendedor/addestado?idPersona=${idPersona}&idEstado=${idEstado}`, null, { responseType: 'text' });
  }
}