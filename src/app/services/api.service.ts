import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// ──────────────────────────────────────────
// INTERFACES — AUTH / TRABAJADORES
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
  idPersona: number; 
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

// ──────────────────────────────────────────
// INTERFACES — INVENTARIO / PRODUCTO
// ──────────────────────────────────────────

export interface ProductoDTO {
  idProducto: number;
  nombre: string;
  precioUnitario: number;
  tipo: string;
}

export interface InventarioDTO {
  idProducto: number;
  stockMinimo: number;
  cantidadProducto: number;
  producto: ProductoDTO;
}

export interface CrearInventarioPayload {
  stockMinimo: number;
  cantidadProducto: number;
  productodto: {
    nombre: string;
    precioUnitario: number;
    tipo: string;
  };
}

export interface ActualizarInventarioPayload {
  stockMinimo: number;
  cantidadProducto: number;
}

// ──────────────────────────────────────────
// INTERFACES — PROVEEDOR
// ──────────────────────────────────────────

export interface ProveedorDTO {
  idEmpresa: number;
  nIT: string;
  nombre: string;
  telefono: string;
  correo: string;
  calificacion: number;
  tipoProveedor: string;
}

export interface CrearProveedorPayload {
  nIT: string;
  nombre: string;
  telefono: string;
  correo: string;
  calificacion: number;
  tipoProveedor: string;
}

// ──────────────────────────────────────────
// INTERFACES — MÁQUINA
// ──────────────────────────────────────────

export interface EstadoMaquinaDTO {
  idEstadoMaquina: number;
  estado: string;
}

export interface MaquinaDTO {
  idMaquina: number;
  numeroSerie: string;
  tipo: string;
  estadoMaquinadto: EstadoMaquinaDTO;
}

export interface CrearMaquinaPayload {
  numeroSerie: string;
  tipo: string;
  estadoMaquinadto: EstadoMaquinaDTO;
}

// ──────────────────────────────────────────
// INTERFACES — PEDIDO MATERIAL
// ──────────────────────────────────────────

export interface PedidoMaterialDTO {
  idPedido: number;
  cantidadMaterial: string;
  fechaPedido: string;
  fechaEntrega: string;
  proveedordto: ProveedorDTO;
}

export interface CrearPedidoPayload {
  cantidadMaterial: string;
  fechaPedido: string;
  fechaEntrega: string;
  proveedordto: { idEmpresa: number };
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

  // ──────────────────────────────────────────
  // INVENTARIO
  // ──────────────────────────────────────────
  getInventario(): Observable<InventarioDTO[]> {
    return this.http.get<InventarioDTO[]>(`${this.BASE_URL}/inventario/getall`);
  }

  crearInventario(datos: CrearInventarioPayload): Observable<string> {
    return this.http.post(`${this.BASE_URL}/producto/addinventario`, datos, { responseType: 'text' });
  }

  actualizarInventario(idProducto: number, datos: ActualizarInventarioPayload): Observable<string> {
    return this.http.put(`${this.BASE_URL}/inventario/update/${idProducto}`, datos, { responseType: 'text' });
  }

  // ──────────────────────────────────────────
  // PROVEEDORES
  // ──────────────────────────────────────────
  getProveedores(): Observable<ProveedorDTO[]> {
    return this.http.get<ProveedorDTO[]>(`${this.BASE_URL}/proveedor/getall`);
  }

  crearProveedor(datos: CrearProveedorPayload): Observable<string> {
    return this.http.post(`${this.BASE_URL}/proveedor/createjson`, datos, { responseType: 'text' });
  }

  editarProveedor(id: number, datos: Partial<CrearProveedorPayload>): Observable<string> {
    return this.http.put(`${this.BASE_URL}/proveedor/update/${id}`, datos, { responseType: 'text' });
  }

  eliminarProveedor(id: number): Observable<string> {
    return this.http.delete(`${this.BASE_URL}/proveedor/delete/${id}`, { responseType: 'text' });
  }

  // ──────────────────────────────────────────
  // PEDIDOS DE MATERIAL
  // ──────────────────────────────────────────
  getPedidos(): Observable<PedidoMaterialDTO[]> {
    return this.http.get<PedidoMaterialDTO[]>(`${this.BASE_URL}/pedidomaterial/getall`);
  }

  crearPedido(datos: CrearPedidoPayload): Observable<string> {
    return this.http.post(`${this.BASE_URL}/pedidomaterial/createjson`, datos, { responseType: 'text' });
  }

  // ──────────────────────────────────────────
  // MÁQUINAS
  // ──────────────────────────────────────────
  getMaquinas(): Observable<MaquinaDTO[]> {
    return this.http.get<MaquinaDTO[]>(`${this.BASE_URL}/maquina/getall`);
  }

  crearMaquina(datos: CrearMaquinaPayload): Observable<string> {
    return this.http.post(`${this.BASE_URL}/maquina/createjson`, datos, { responseType: 'text' });
  }

  editarMaquina(id: number, datos: Partial<CrearMaquinaPayload>): Observable<string> {
    return this.http.put(`${this.BASE_URL}/maquina/update/${id}`, datos, { responseType: 'text' });
  }

  cambiarEstadoMaquina(idMaquina: number, idEstado: number): Observable<string> {
    return this.http.post(`${this.BASE_URL}/maquina/addestado?idMaquina=${idMaquina}&idEstado=${idEstado}`, null, { responseType: 'text' });
  }

  eliminarMaquina(id: number): Observable<string> {
    return this.http.delete(`${this.BASE_URL}/maquina/delete/${id}`, { responseType: 'text' });
  }
}