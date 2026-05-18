import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

// ── Interfaces alineadas con el MER ──────────────────
export interface ProveedorItem {
  idEmpresa: number;
  nIT: string;
  nombre: string;
  telefono: string;
  correo: string;
  tipoProveedor: 'Nacional' | 'Internacional';
  calificacion: number;
  tiempoPromEntrega: string;
  activo: boolean;
}

export interface PedidoMaterialItem {
  idPedido: number;
  idEmpresa: number;
  nombreProveedor: string;
  idMaterial: number;
  nombreMaterial: string;
  cantidadMaterial: number;
  fechaPedido: string;
  fechaEntrega: string;
}

@Component({
  selector: 'app-proveedores',
  standalone: true,
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './proveedores.html',
  styleUrl: './proveedores.css',
})
export class Proveedores implements OnInit {

  // ── Datos de muestra (se reemplazan al conectar el back) ──
  proveedores: ProveedorItem[] = [
    { idEmpresa: 1, nIT: '900123456-1', nombre: 'Autolux SAS',   telefono: '3101234567', correo: 'autolux@mail.com',  tipoProveedor: 'Nacional',       calificacion: 4.8, tiempoPromEntrega: '3 días',  activo: true  },
    { idEmpresa: 2, nIT: '800987654-2', nombre: 'Lumix Import',  telefono: '3209876543', correo: 'lumix@import.com', tipoProveedor: 'Internacional',  calificacion: 4.2, tiempoPromEntrega: '10 días', activo: false },
  ];

  pedidos: PedidoMaterialItem[] = [
    { idPedido: 1, idEmpresa: 1, nombreProveedor: 'Autolux SAS',  idMaterial: 1, nombreMaterial: 'Bombillo Xenon', cantidadMaterial: 250, fechaPedido: '2026-05-10', fechaEntrega: '2026-05-14' },
    { idPedido: 2, idEmpresa: 2, nombreProveedor: 'Lumix Import', idMaterial: 2, nombreMaterial: 'Cable Arnés',    cantidadMaterial: 120, fechaPedido: '2026-05-12', fechaEntrega: '2026-05-20' },
  ];

  // Lista de materiales disponibles para el select de pedido
  // TODO: cargar desde /material/getall
  materialesDisponibles = [
    { idMaterial: 1, nombre: 'Bombillo Xenon D2S' },
    { idMaterial: 2, nombre: 'Cable Arnés' },
    { idMaterial: 3, nombre: 'Kit Xenon H7' },
  ];

  // ── Modales ──────────────────────────────────────────
  mostrarCrearProveedor  = false;
  mostrarPedidoMaterial  = false;
  mostrarEstadoProveedor = false;
  mostrarEditarProveedor = false;

  // ── Formulario Crear Proveedor ────────────────────────
  nuevoNIT              = '';
  nuevoNombre           = '';
  nuevoTelefono         = '';
  nuevoCorreo           = '';
  nuevoTipo: 'Nacional' | 'Internacional' | '' = '';
  nuevoCalificacion: number | null = null;
  nuevoTiempoEntrega    = '';

  // ── Formulario Editar ─────────────────────────────────
  editId: number | null = null;
  editNombre        = '';
  editTelefono      = '';
  editCorreo        = '';
  editCalificacion: number | null = null;
  editTiempoEntrega = '';

  // ── Formulario Pedido ─────────────────────────────────
  pedidoProveedorId: number | null = null;
  pedidoMaterialId: number | null  = null;
  pedidoCantidad: number | null    = null;
  pedidoFecha       = '';
  pedidoFechaEntrega = '';

  // ── Formulario Estado ─────────────────────────────────
  estadoProveedorId: number | null = null;
  nuevoEstadoActivo: boolean | null = null;

  // ── Mensajes ──────────────────────────────────────────
  mensaje     = '';
  tipoMensaje: 'error' | 'success' | '' = '';
  guardando   = false;

  // ── Getters para cards ────────────────────────────────
  get activos():   number { return this.proveedores.filter(p => p.activo).length; }
  get inactivos(): number { return this.proveedores.filter(p => !p.activo).length; }

  ngOnInit(): void {
    // TODO: this.api.getProveedores().subscribe(data => this.proveedores = data)
    // TODO: this.api.getPedidos().subscribe(data => this.pedidos = data)
  }

  // ── Helpers ───────────────────────────────────────────
  mostrarMsg(texto: string, tipo: 'error' | 'success'): void {
    this.mensaje = texto; this.tipoMensaje = tipo;
    if (tipo === 'success') setTimeout(() => { this.mensaje = ''; this.tipoMensaje = ''; }, 3000);
  }

  // ── Abrir modales ─────────────────────────────────────
  abrirCrearProveedor(): void {
    this.cerrarModales();
    this.nuevoNIT = ''; this.nuevoNombre = ''; this.nuevoTelefono = '';
    this.nuevoCorreo = ''; this.nuevoTipo = ''; this.nuevoCalificacion = null; this.nuevoTiempoEntrega = '';
    this.mostrarCrearProveedor = true;
  }

  abrirPedidoMaterial(): void {
    this.cerrarModales();
    this.pedidoProveedorId = null; this.pedidoMaterialId = null;
    this.pedidoCantidad = null; this.pedidoFecha = ''; this.pedidoFechaEntrega = '';
    this.mostrarPedidoMaterial = true;
  }

  abrirEstadoProveedor(): void {
    this.cerrarModales();
    this.estadoProveedorId = null; this.nuevoEstadoActivo = null;
    this.mostrarEstadoProveedor = true;
  }

  abrirEditarProveedor(p: ProveedorItem): void {
    this.cerrarModales();
    this.editId           = p.idEmpresa;
    this.editNombre       = p.nombre;
    this.editTelefono     = p.telefono;
    this.editCorreo       = p.correo;
    this.editCalificacion = p.calificacion;
    this.editTiempoEntrega = p.tiempoPromEntrega;
    this.mostrarEditarProveedor = true;
  }

  cerrarModales(): void {
    this.mostrarCrearProveedor = false; this.mostrarPedidoMaterial = false;
    this.mostrarEstadoProveedor = false; this.mostrarEditarProveedor = false;
    this.mensaje = ''; this.tipoMensaje = '';
  }

  // ── CRUD ──────────────────────────────────────────────
  guardarProveedor(): void {
    if (!this.nuevoNIT.trim())    { this.mostrarMsg('El NIT es obligatorio.', 'error'); return; }
    if (!this.nuevoNombre.trim()) { this.mostrarMsg('El nombre es obligatorio.', 'error'); return; }
    if (!this.nuevoTelefono.trim()) { this.mostrarMsg('El teléfono es obligatorio.', 'error'); return; }
    const emailRx = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRx.test(this.nuevoCorreo)) { this.mostrarMsg('Ingresa un correo válido.', 'error'); return; }
    if (!this.nuevoTipo)          { this.mostrarMsg('Selecciona el tipo de proveedor.', 'error'); return; }
    if (!this.nuevoCalificacion || this.nuevoCalificacion < 0 || this.nuevoCalificacion > 5)
      { this.mostrarMsg('La calificación debe ser entre 0 y 5.', 'error'); return; }

    // TODO: this.api.crearProveedor({...}).subscribe(...)
    this.proveedores.push({
      idEmpresa: this.proveedores.length + 1,
      nIT: this.nuevoNIT, nombre: this.nuevoNombre, telefono: this.nuevoTelefono,
      correo: this.nuevoCorreo, tipoProveedor: this.nuevoTipo as 'Nacional' | 'Internacional',
      calificacion: this.nuevoCalificacion, tiempoPromEntrega: this.nuevoTiempoEntrega, activo: true,
    });
    this.mostrarMsg('Proveedor creado correctamente.', 'success');
    setTimeout(() => this.cerrarModales(), 1800);
  }

  guardarEdicion(): void {
    if (!this.editNombre.trim()) { this.mostrarMsg('El nombre es obligatorio.', 'error'); return; }
    const emailRx = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRx.test(this.editCorreo)) { this.mostrarMsg('Correo inválido.', 'error'); return; }

    // TODO: this.api.editarProveedor(this.editId!, {...}).subscribe(...)
    const p = this.proveedores.find(x => x.idEmpresa === this.editId);
    if (p) {
      p.nombre = this.editNombre; p.telefono = this.editTelefono;
      p.correo = this.editCorreo; p.calificacion = this.editCalificacion ?? p.calificacion;
      p.tiempoPromEntrega = this.editTiempoEntrega || p.tiempoPromEntrega;
    }
    this.mostrarMsg('Proveedor actualizado.', 'success');
    setTimeout(() => this.cerrarModales(), 1800);
  }

  registrarPedido(): void {
    if (!this.pedidoProveedorId) { this.mostrarMsg('Selecciona un proveedor.', 'error'); return; }
    if (!this.pedidoMaterialId)  { this.mostrarMsg('Selecciona un material.', 'error'); return; }
    if (!this.pedidoCantidad || this.pedidoCantidad <= 0) { this.mostrarMsg('La cantidad debe ser mayor a 0.', 'error'); return; }
    if (!this.pedidoFecha)       { this.mostrarMsg('La fecha de pedido es obligatoria.', 'error'); return; }

    // TODO: this.api.crearPedido({...}).subscribe(...)
    const prov = this.proveedores.find(p => p.idEmpresa === Number(this.pedidoProveedorId));
    const mat  = this.materialesDisponibles.find(m => m.idMaterial === Number(this.pedidoMaterialId));
    this.pedidos.push({
      idPedido: this.pedidos.length + 1,
      idEmpresa: Number(this.pedidoProveedorId), nombreProveedor: prov?.nombre ?? '',
      idMaterial: Number(this.pedidoMaterialId), nombreMaterial: mat?.nombre ?? '',
      cantidadMaterial: this.pedidoCantidad, fechaPedido: this.pedidoFecha, fechaEntrega: this.pedidoFechaEntrega,
    });
    this.mostrarMsg('Pedido registrado correctamente.', 'success');
    setTimeout(() => this.cerrarModales(), 1800);
  }

  cambiarEstado(): void {
    if (!this.estadoProveedorId)        { this.mostrarMsg('Selecciona un proveedor.', 'error'); return; }
    if (this.nuevoEstadoActivo === null) { this.mostrarMsg('Selecciona el nuevo estado.', 'error'); return; }

    // TODO: this.api.cambiarEstadoProveedor(this.estadoProveedorId!, ...).subscribe(...)
    const p = this.proveedores.find(x => x.idEmpresa === Number(this.estadoProveedorId));
    if (p) p.activo = this.nuevoEstadoActivo as boolean;
    this.mostrarMsg('Estado actualizado.', 'success');
    setTimeout(() => this.cerrarModales(), 1800);
  }

  eliminarProveedor(id: number): void {
    if (!confirm('¿Seguro que deseas eliminar este proveedor?')) return;
    // TODO: this.api.eliminarProveedor(id).subscribe(...)
    this.proveedores = this.proveedores.filter(p => p.idEmpresa !== id);
  }
}