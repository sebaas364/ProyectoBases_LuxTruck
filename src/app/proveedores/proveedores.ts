import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { forkJoin } from 'rxjs';
import {
  ApiService,
  ProveedorDTO,
  PedidoMaterialDTO,
  CrearProveedorPayload,
  CrearPedidoPayload,
} from '../services/api.service';

@Component({
  selector: 'app-proveedores',
  standalone: true,
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './proveedores.html',
  styleUrl: './proveedores.css',
})
export class Proveedores implements OnInit {

  proveedores: ProveedorDTO[] = [];
  pedidos: PedidoMaterialDTO[] = [];
  cargando = true;

  // Modales
  mostrarCrearProveedor  = false;
  mostrarPedidoMaterial  = false;
  mostrarEditarProveedor = false;
  mostrarEstadoProveedor = false;

  // Formulario Crear Proveedor
  nuevoNIT          = '';
  nuevoNombre       = '';;
  nuevoTelefono     = '';
  nuevoCorreo       = '';
  nuevoTipo         = '';
  nuevoCalificacion: number | null = null;

  // Formulario Editar Proveedor
  editProveedorId: number | null = null;
  editNIT          = '';
  editNombre       = '';
  editTelefono     = '';
  editCorreo       = '';
  editTipo         = '';
  editCalificacion: number | null = null;
  pedidoProveedorId: number | null = null;
  pedidoMaterial    = '';
  pedidoCantidad    = '';
  pedidoFecha       = '';
  pedidoFechaEntrega = '';

  mensaje     = '';
  tipoMensaje: 'error' | 'success' | '' = '';
  guardando   = false;

  constructor(private api: ApiService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.cargando = true;
    forkJoin({
      proveedores: this.api.getProveedores(),
      pedidos:     this.api.getPedidos()
    }).subscribe({
      next: ({ proveedores, pedidos }) => {
        this.proveedores = proveedores || [];
        this.pedidos     = pedidos || [];
        this.cargando    = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.mostrarMsg('Error al conectar con el servidor. ¿Está corriendo el backend?', 'error');
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  mostrarMsg(texto: string, tipo: 'error' | 'success'): void {
    this.mensaje = texto;
    this.tipoMensaje = tipo;
    if (tipo === 'success') {
      setTimeout(() => { this.mensaje = ''; this.tipoMensaje = ''; this.cdr.detectChanges(); }, 3000);
    }
    this.cdr.detectChanges();
  }

  abrirCrearProveedor(): void {
    this.cerrarModales();
    this.nuevoNIT = '';
    this.nuevoNombre = '';
    this.nuevoTelefono = '';
    this.nuevoCorreo = '';
    this.nuevoTipo = '';
    this.nuevoCalificacion = null;
    this.mostrarCrearProveedor = true;
  }

  abrirEditarProveedor(): void {
    this.cerrarModales();
    this.editProveedorId = null;
    this.editNIT = '';
    this.editNombre = '';
    this.editTelefono = '';
    this.editCorreo = '';
    this.editTipo = '';
    this.editCalificacion = null;
    this.mostrarEditarProveedor = true;
  }

  onSeleccionarProveedor(): void {
    if (!this.editProveedorId) return;
    const p = this.proveedores.find(x => x.idEmpresa === this.editProveedorId);
    if (!p) return;
    this.editNIT          = p.NIT || '';
    this.editNombre       = p.nombre;
    this.editTelefono     = p.telefono;
    this.editCorreo       = p.correo;
    this.editTipo         = p.tipoProveedor;
    this.editCalificacion = p.calificacion;
  }

  guardarEdicionProveedor(): void {
    if (!this.editProveedorId) { this.mostrarMsg('Selecciona un proveedor.', 'error'); return; }
    if (!this.editNombre.trim()) { this.mostrarMsg('El nombre es obligatorio.', 'error'); return; }
    if (!this.editTipo) { this.mostrarMsg('Selecciona el tipo de proveedor.', 'error'); return; }

    const payload = {
      NIT:           this.editNIT.trim(),
      nombre:        this.editNombre.trim(),
      telefono:      this.editTelefono.trim(),
      correo:        this.editCorreo.trim(),
      tipoProveedor: this.editTipo,
      calificacion:  this.editCalificacion ?? 0,
    };

    this.guardando = true;
    this.api.editarProveedor(this.editProveedorId, payload).subscribe({
      next: () => {
        this.guardando = false;
        this.mostrarMsg('Proveedor actualizado exitosamente.', 'success');
        this.cargar();
        setTimeout(() => this.cerrarModales(), 2000);
      },
      error: (err: HttpErrorResponse) => {
        this.guardando = false;
        console.error('Error al editar proveedor:', err);
        this.mostrarMsg('Error al actualizar. Verifica los datos.', 'error');
      }
    });
  }

  abrirPedidoMaterial(): void {
    this.cerrarModales();
    this.pedidoProveedorId = null;
    this.pedidoMaterial = '';
    this.pedidoCantidad = '';
    this.pedidoFecha = '';
    this.pedidoFechaEntrega = '';
    this.mostrarPedidoMaterial = true;
  }

  cerrarModales(): void {
    this.mostrarCrearProveedor  = false;
    this.mostrarPedidoMaterial  = false;
    this.mostrarEditarProveedor = false;
    this.mostrarEstadoProveedor = false;
    this.mensaje = '';
    this.tipoMensaje = '';
  }

  guardarProveedor(): void {
    if (!this.nuevoNombre.trim()) { this.mostrarMsg('El nombre es obligatorio.', 'error'); return; }
    if (!this.nuevoTipo)          { this.mostrarMsg('Selecciona el tipo de proveedor.', 'error'); return; }

    // IMPORTANTE: el campo es "NIT" (mayúsculas) porque el getter Java es getNIT()
    const payload: CrearProveedorPayload = {
      NIT:           this.nuevoNIT.trim(),
      nombre:        this.nuevoNombre.trim(),
      telefono:      this.nuevoTelefono.trim(),
      correo:        this.nuevoCorreo.trim(),
      tipoProveedor: this.nuevoTipo,
      calificacion:  this.nuevoCalificacion ?? 0,
    };

    this.guardando = true;
    this.api.crearProveedor(payload).subscribe({
      next: () => {
        this.guardando = false;
        this.mostrarMsg('Proveedor guardado exitosamente.', 'success');
        this.cargar();
        setTimeout(() => this.cerrarModales(), 2000);
      },
      error: (err: HttpErrorResponse) => {
        this.guardando = false;
        console.error('Error al crear proveedor:', err);
        this.mostrarMsg('Error al guardar el proveedor. Verifica los datos en consola.', 'error');
      }
    });
  }

  registrarPedido(): void {
    if (!this.pedidoProveedorId)       { this.mostrarMsg('Selecciona un proveedor.', 'error'); return; }
    if (!this.pedidoMaterial.trim())   { this.mostrarMsg('El material es requerido.', 'error'); return; }
    if (!this.pedidoCantidad.trim())   { this.mostrarMsg('La cantidad es requerida.', 'error'); return; }
    if (!this.pedidoFecha)             { this.mostrarMsg('La fecha del pedido es requerida.', 'error'); return; }

    const payload: CrearPedidoPayload = {
      cantidadMaterial: this.pedidoCantidad.trim(),
      fechaPedido:      this.pedidoFecha,
      fechaEntrega:     this.pedidoFechaEntrega || this.pedidoFecha,
      // El setter setProveedor() → debe enviarse como "proveedor"
      proveedor:        { idEmpresa: Number(this.pedidoProveedorId) }
    };

    this.guardando = true;
    this.api.crearPedido(payload).subscribe({
      next: () => {
        this.guardando = false;
        this.mostrarMsg('Pedido registrado exitosamente.', 'success');
        this.cargar();
        setTimeout(() => this.cerrarModales(), 2000);
      },
      error: (err: HttpErrorResponse) => {
        this.guardando = false;
        console.error('Error al registrar pedido:', err);
        this.mostrarMsg('Error al registrar el pedido.', 'error');
      }
    });
  }

  eliminarProveedor(id: number): void {
    if (!confirm('¿Deseas eliminar este proveedor? Esta acción no se puede deshacer.')) return;
    this.api.eliminarProveedor(id).subscribe({
      next: () => {
        this.mostrarMsg('Proveedor eliminado correctamente.', 'success');
        this.cargar();
      },
      error: () => this.mostrarMsg('Error al eliminar. El proveedor puede tener pedidos activos.', 'error')
    });
  }
}