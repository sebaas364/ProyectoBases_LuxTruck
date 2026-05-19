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
    if (!this.nuevoNIT.trim())                              { this.mostrarMsg('El NIT es obligatorio.', 'error'); return; }
    if (!/^\d{9,10}-\d$/.test(this.nuevoNIT.trim()))       { this.mostrarMsg('NIT inválido. Formato: 123456789-0', 'error'); return; }
    if (!this.nuevoNombre.trim())                           { this.mostrarMsg('El nombre es obligatorio.', 'error'); return; }
    if (!/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/.test(this.nuevoNombre.trim())) { this.mostrarMsg('El nombre solo debe contener letras.', 'error'); return; }
    if (!this.nuevoTelefono.trim())                         { this.mostrarMsg('El teléfono es obligatorio.', 'error'); return; }
    if (!/^\d{7,10}$/.test(this.nuevoTelefono.trim()))     { this.mostrarMsg('Teléfono inválido. Solo dígitos, entre 7 y 10.', 'error'); return; }
    if (!this.nuevoCorreo.trim())                           { this.mostrarMsg('El correo es obligatorio.', 'error'); return; }
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.nuevoCorreo.trim())) { this.mostrarMsg('Correo inválido.', 'error'); return; }
    if (!this.nuevoTipo)                                    { this.mostrarMsg('Selecciona el tipo de proveedor.', 'error'); return; }
    if (this.nuevoCalificacion === null || this.nuevoCalificacion === undefined) { this.mostrarMsg('La calificación es obligatoria.', 'error'); return; }
    if (isNaN(Number(this.nuevoCalificacion)))              { this.mostrarMsg('La calificación debe ser un número.', 'error'); return; }
    if (this.nuevoCalificacion < 0 || this.nuevoCalificacion > 5) { this.mostrarMsg('La calificación debe estar entre 0 y 5.', 'error'); return; }

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
    if (!this.pedidoProveedorId)                            { this.mostrarMsg('Selecciona un proveedor.', 'error'); return; }
    if (!this.pedidoCantidad.trim())                        { this.mostrarMsg('La cantidad es requerida.', 'error'); return; }
    if (!/^\d+$/.test(this.pedidoCantidad.trim()))          { this.mostrarMsg('La cantidad solo debe contener números enteros.', 'error'); return; }
    if (parseInt(this.pedidoCantidad.trim()) <= 0)          { this.mostrarMsg('La cantidad debe ser mayor a 0.', 'error'); return; }
    if (!this.pedidoFecha)                                  { this.mostrarMsg('La fecha del pedido es requerida.', 'error'); return; }
    if (this.pedidoFechaEntrega && this.pedidoFechaEntrega <= this.pedidoFecha) { this.mostrarMsg('La fecha de entrega debe ser posterior a la fecha del pedido.', 'error'); return; }

    const payload: CrearPedidoPayload = {
      cantidadMaterial: this.pedidoCantidad.trim(),
      fechaPedido:      this.pedidoFecha ? new Date(this.pedidoFecha + 'T12:00:00').getTime() : 0,
      fechaEntrega:     this.pedidoFechaEntrega
                          ? new Date(this.pedidoFechaEntrega + 'T12:00:00').getTime()
                          : new Date(this.pedidoFecha + 'T12:00:00').getTime(),
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
        // El backend devuelve 406 aunque guarda correctamente (bug conocido del backend)
        // Si es 406, tratamos como éxito y recargamos
        if (err.status === 406) {
          this.mostrarMsg('Pedido registrado exitosamente.', 'success');
          this.cargar();
          setTimeout(() => this.cerrarModales(), 2000);
        } else {
          this.mostrarMsg('Error al registrar el pedido.', 'error');
        }
      }
    });
  }

  eliminarPedido(id: number): void {
    if (!confirm('¿Deseas eliminar este pedido? Esta acción no se puede deshacer.')) return;
    this.api.eliminarPedido(id).subscribe({
      next: () => {
        this.mostrarMsg('Pedido eliminado correctamente.', 'success');
        this.cargar();
      },
      error: () => this.mostrarMsg('Error al eliminar el pedido.', 'error')
    });
  }

  eliminarProveedor(id: number): void {
    const hoy = new Date();
    hoy.setHours(0, 0, 0, 0);
    const pedidosActivos = this.pedidos.filter(p =>
      p.proveedor?.idEmpresa === id &&
      (!p.fechaEntrega || new Date(p.fechaEntrega) >= hoy)
    );
    if (pedidosActivos.length > 0) {
      this.mostrarMsg('No se puede eliminar: el proveedor tiene pedidos con fecha de entrega vigente.', 'error');
      return;
    }
    if (!confirm('¿Deseas eliminar este proveedor? Esta acción no se puede deshacer.')) return;
    this.api.eliminarProveedor(id).subscribe({
      next: () => {
        this.mostrarMsg('Proveedor eliminado correctamente.', 'success');
        this.cargar();
      },
      error: () => this.mostrarMsg('No se puede eliminar: este proveedor tiene pedidos de material asociados.', 'error')
    });
  }
}