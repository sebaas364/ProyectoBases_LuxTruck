import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { forkJoin } from 'rxjs';
import { ApiService, ProveedorDTO, PedidoMaterialDTO } from '../services/api.service';

@Component({
  selector: 'app-proveedores',
  standalone: true,
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './proveedores.html',
  styleUrl: './proveedores.css',
})
export class Proveedores implements OnInit {

  proveedores: any[] = [];
  pedidos:     any[] = [];
  cargando = true;

  // Modales
  mostrarCrearProveedor  = false;
  mostrarPedidoMaterial  = false;
  mostrarEditarProveedor = false;
  mostrarEstadoProveedor = false;

  // Formulario Crear
  nuevoNIT           = '';
  nuevoNombre        = '';
  nuevoTelefono      = '';
  nuevoCorreo        = '';
  nuevoTipo          = '';
  nuevoTiempoEntrega = '';
  nuevoCalificacion: number | null = null;

  // Formulario Pedido
  pedidoProveedorId: number | null = null;
  pedidoMaterial    = '';
  pedidoCantidad    = '';
  pedidoFecha       = '';
  pedidoFechaEntrega = '';

  mensaje     = '';
  tipoMensaje: 'error' | 'success' | '' = '';
  guardando   = false;

  // Contadores dinámicos calculados de la lista real
  get activos():   number { return this.proveedores.filter(p => p.estado?.toLowerCase() !== 'inactivo').length; }
  get inactivos(): number { return this.proveedores.filter(p => p.estado?.toLowerCase() === 'inactivo').length; }
  get totalMateriales(): number { return this.pedidos.length; }

  constructor(private api: ApiService) {}

  ngOnInit(): void { this.cargar(); }

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
      },
      error: () => {
        this.mostrarMsg('Error al conectar con el servidor.', 'error');
        this.cargando = false;
      }
    });
  }

  mostrarMsg(texto: string, tipo: 'error' | 'success'): void {
    this.mensaje = texto; this.tipoMensaje = tipo;
    if (tipo === 'success') {
      setTimeout(() => { this.mensaje = ''; this.tipoMensaje = ''; }, 3000);
    }
  }

  abrirCrearProveedor(): void {
    this.cerrarModales();
    this.nuevoNIT = ''; this.nuevoNombre = ''; this.nuevoTelefono = '';
    this.nuevoCorreo = ''; this.nuevoTipo = ''; this.nuevoTiempoEntrega = ''; this.nuevoCalificacion = null;
    this.mostrarCrearProveedor = true;
  }

  abrirPedidoMaterial(): void {
    this.cerrarModales();
    this.pedidoProveedorId = null; this.pedidoMaterial = ''; this.pedidoCantidad = ''; this.pedidoFecha = ''; this.pedidoFechaEntrega = '';
    this.mostrarPedidoMaterial = true;
  }

  cerrarModales(): void {
    this.mostrarCrearProveedor  = false; 
    this.mostrarPedidoMaterial  = false;
    this.mostrarEditarProveedor = false;
    this.mostrarEstadoProveedor = false;
    this.mensaje = ''; this.tipoMensaje = '';
  }

  guardarProveedor(): void {
    if (!this.nuevoNombre.trim()) { this.mostrarMsg('El nombre es obligatorio.', 'error'); return; }
    if (!this.nuevoTipo) { this.mostrarMsg('Selecciona el tipo.', 'error'); return; }

    const idEmpresaTemporal = Math.floor(600000 + Math.random() * 300000);

    const payload: any = {
      idEmpresa: idEmpresaTemporal,
      nIT: this.nuevoNIT.trim(),
      nombre: this.nuevoNombre.trim(),
      telefono: this.nuevoTelefono.trim(),
      correo: this.nuevoCorreo.trim(),
      tipoProveedor: this.nuevoTipo,
      tiempoEntrega: this.nuevoTiempoEntrega.trim(),
      calificacion: this.nuevoCalificacion ?? 0,
      estado: 'Activo'
    };

    this.guardando = true;
    this.api.crearProveedor(payload).subscribe({
      next: () => {
        this.guardando = false;
        this.mostrarMsg('Proveedor guardado.', 'success');
        this.cargar();
        setTimeout(() => this.cerrarModales(), 1000);
      },
      error: () => { this.guardando = false; this.mostrarMsg('Error al guardar.', 'error'); }
    });
  }

  registrarPedido(): void {
    if (!this.pedidoProveedorId) { this.mostrarMsg('Selecciona un proveedor.', 'error'); return; }
    if (!this.pedidoMaterial.trim()) { this.mostrarMsg('El material es requerido.', 'error'); return; }

    const payload: any = {
      nombreMaterial: this.pedidoMaterial.trim(),
      cantidadMaterial: this.pedidoCantidad.trim(),
      fechaPedido: this.pedidoFecha,
      fechaEntrega: this.pedidoFechaEntrega || this.pedidoFecha,
      proveedordto: { idEmpresa: Number(this.pedidoProveedorId) }
    };

    this.guardando = true;
    this.api.crearPedido(payload).subscribe({
      next: () => {
        this.guardando = false;
        this.mostrarMsg('Pedido registrado.', 'success');
        this.cargar();
        setTimeout(() => this.cerrarModales(), 1000);
      },
      error: () => { this.guardando = false; this.mostrarMsg('Error al crear pedido.', 'error'); }
    });
  }

  eliminarProveedor(id: number): void {
    if (!confirm('¿Deseas eliminar este proveedor?')) return;
    this.api.eliminarProveedor(id).subscribe({
      next: () => { this.mostrarMsg('Eliminado.', 'success'); this.cargar(); },
      error: () => this.mostrarMsg('Error al eliminar.', 'error')
    });
  }

  abrirEstadoProveedor(): void { this.mostrarEstadoProveedor = true; }
}