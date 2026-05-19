import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { forkJoin } from 'rxjs';
import {
  ApiService,
  VentaDTO,
  CrearVentaPayload,
  VendedorDTO,
} from '../services/api.service';

@Component({
  selector: 'app-ventas',
  standalone: true,
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './ventas.html',
  styleUrl: './ventas.css',
})
export class Ventas implements OnInit {

  ventas: VentaDTO[]     = [];
  vendedores: VendedorDTO[] = [];
  cargando = true;

  // Modales
  mostrarCrearVenta  = false;
  mostrarEditarVenta = false;

  // Formulario crear
  nuevaFecha       = '';
  nuevaMetodoPago  = '';
  nuevaVendedorId: number | null = null;

  // Formulario editar
  editVentaId: number | null = null;
  editFecha       = '';
  editMetodoPago  = '';

  mensaje     = '';
  tipoMensaje: 'error' | 'success' | '' = '';
  guardando   = false;

  readonly METODOS_PAGO = ['Efectivo', 'Tarjeta débito', 'Tarjeta crédito', 'Transferencia'];

  constructor(private api: ApiService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.cargando = true;
    forkJoin({
      ventas:     this.api.getVentas(),
      vendedores: this.api.getVendedores(),
    }).subscribe({
      next: ({ ventas, vendedores }) => {
        this.ventas     = ventas     || [];
        this.vendedores = vendedores || [];
        this.cargando   = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.mostrarMsg('Error al conectar con el servidor.', 'error');
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  mostrarMsg(texto: string, tipo: 'error' | 'success'): void {
    this.mensaje     = texto;
    this.tipoMensaje = tipo;
    if (tipo === 'success') {
      setTimeout(() => { this.mensaje = ''; this.tipoMensaje = ''; this.cdr.detectChanges(); }, 3000);
    }
    this.cdr.detectChanges();
  }

  abrirCrearVenta(): void {
    this.cerrarModales();
    this.nuevaFecha      = '';
    this.nuevaMetodoPago = '';
    this.nuevaVendedorId = null;
    this.mostrarCrearVenta = true;
  }

  abrirEditarVenta(): void {
    this.cerrarModales();
    this.editVentaId    = null;
    this.editFecha      = '';
    this.editMetodoPago = '';
    this.mostrarEditarVenta = true;
  }

  onSeleccionarVenta(): void {
    if (!this.editVentaId) return;
    const v = this.ventas.find(x => x.idVenta === this.editVentaId);
    if (!v) return;
    // fecha viene como timestamp, convertir a yyyy-MM-dd para el input date
    this.editFecha      = v.fecha ? new Date(v.fecha).toISOString().substring(0, 10) : '';
    this.editMetodoPago = v.metodoPago || '';
  }

  cerrarModales(): void {
    this.mostrarCrearVenta  = false;
    this.mostrarEditarVenta = false;
    this.mensaje     = '';
    this.tipoMensaje = '';
  }

  guardarVenta(): void {
    if (!this.nuevaFecha)           { this.mostrarMsg('La fecha es obligatoria.', 'error'); return; }
    if (!this.nuevaMetodoPago)      { this.mostrarMsg('Selecciona un método de pago.', 'error'); return; }
    if (!this.nuevaVendedorId)      { this.mostrarMsg('Selecciona un vendedor.', 'error'); return; }

    const fechaDate = new Date(this.nuevaFecha + 'T12:00:00');
    if (isNaN(fechaDate.getTime())) { this.mostrarMsg('Fecha inválida.', 'error'); return; }

    const payload: CrearVentaPayload = {
      fecha:       fechaDate.getTime(),
      metodoPago:  this.nuevaMetodoPago,
      vendedordto: { idPersona: Number(this.nuevaVendedorId) },
    };

    this.guardando = true;
    this.api.crearVenta(payload).subscribe({
      next: () => {
        this.guardando = false;
        this.mostrarMsg('Venta registrada exitosamente.', 'success');
        this.cargar();
        setTimeout(() => this.cerrarModales(), 2000);
      },
      error: (err: HttpErrorResponse) => {
        this.guardando = false;
        if (err.status === 406) {
          this.mostrarMsg('Venta registrada exitosamente.', 'success');
          this.cargar();
          setTimeout(() => this.cerrarModales(), 2000);
        } else {
          this.mostrarMsg('Error al registrar la venta.', 'error');
        }
      }
    });
  }

  guardarEdicionVenta(): void {
    if (!this.editVentaId)        { this.mostrarMsg('Selecciona una venta.', 'error'); return; }
    if (!this.editFecha)          { this.mostrarMsg('La fecha es obligatoria.', 'error'); return; }
    if (!this.editMetodoPago)     { this.mostrarMsg('Selecciona un método de pago.', 'error'); return; }

    const fechaDate = new Date(this.editFecha + 'T12:00:00');
    if (isNaN(fechaDate.getTime())) { this.mostrarMsg('Fecha inválida.', 'error'); return; }

    const payload = {
      fecha:      fechaDate.getTime(),
      metodoPago: this.editMetodoPago,
    };

    this.guardando = true;
    this.api.editarVenta(this.editVentaId, payload).subscribe({
      next: () => {
        this.guardando = false;
        this.mostrarMsg('Venta actualizada exitosamente.', 'success');
        this.cargar();
        setTimeout(() => this.cerrarModales(), 2000);
      },
      error: () => {
        this.guardando = false;
        this.mostrarMsg('Error al actualizar la venta.', 'error');
      }
    });
  }

  eliminarVenta(id: number): void {
    if (!confirm('¿Deseas eliminar esta venta? Esta acción no se puede deshacer.')) return;
    this.api.eliminarVenta(id).subscribe({
      next: () => {
        this.mostrarMsg('Venta eliminada correctamente.', 'success');
        this.cargar();
      },
      error: () => this.mostrarMsg('Error al eliminar la venta.', 'error')
    });
  }

  nombreVendedor(v: VentaDTO): string {
    if (!v.vendedordto) return '—';
    return `${v.vendedordto.primerNombre ?? ''} ${v.vendedordto.primerApellido ?? ''}`.trim() || '—';
  }
}