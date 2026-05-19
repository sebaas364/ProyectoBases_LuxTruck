import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { forkJoin } from 'rxjs';
import {
  ApiService,
  VentaDTO,
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

  ventas: VentaDTO[]        = [];
  vendedores: VendedorDTO[] = [];
  cargando = true;

  mostrarCrearVenta  = false;
  mostrarEditarVenta = false;

  nuevaFecha       = '';
  nuevaMetodoPago  = '';
  nuevaVendedorId: number | null = null;

  editVentaId: number | null = null;
  editFecha       = '';
  editMetodoPago  = '';

  mensaje     = '';
  tipoMensaje: 'error' | 'success' | '' = '';
  guardando   = false;

  readonly METODOS_PAGO = ['Efectivo', 'Tarjeta débito', 'Tarjeta crédito', 'Transferencia'];

  constructor(private api: ApiService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void { this.cargar(); }

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
    this.mensaje = texto; this.tipoMensaje = tipo;
    if (tipo === 'success') {
      setTimeout(() => { this.mensaje = ''; this.tipoMensaje = ''; this.cdr.detectChanges(); }, 3000);
    }
    this.cdr.detectChanges();
  }

  abrirCrearVenta(): void {
    this.cerrarModales();
    this.nuevaFecha = ''; this.nuevaMetodoPago = ''; this.nuevaVendedorId = null;
    this.mostrarCrearVenta = true;
  }

  abrirEditarVenta(): void {
    this.cerrarModales();
    this.editVentaId = null; this.editFecha = ''; this.editMetodoPago = '';
    this.mostrarEditarVenta = true;
  }

  onSeleccionarVenta(): void {
    if (!this.editVentaId) return;
    const v = this.ventas.find(x => x.idVenta === this.editVentaId);
    if (!v) return;
    this.editFecha      = v.fecha ? new Date(v.fecha).toISOString().substring(0, 10) : '';
    this.editMetodoPago = v.metodoPago || '';
  }

  cerrarModales(): void {
    this.mostrarCrearVenta = false; this.mostrarEditarVenta = false;
    this.mensaje = ''; this.tipoMensaje = '';
  }

  // Flujo 2 pasos:
  // 1) POST /venta/createjson  → crea la venta (sin vendedor, ID auto)
  // 2) Recargamos ventas para obtener el nuevo idVenta
  // 3) POST /venta/addvendedor?idVenta=X&idVendedor=Y
  guardarVenta(): void {
    if (!this.nuevaFecha)      { this.mostrarMsg('La fecha es obligatoria.', 'error'); return; }
    if (!this.nuevaMetodoPago) { this.mostrarMsg('Selecciona un método de pago.', 'error'); return; }
    if (!this.nuevaVendedorId) { this.mostrarMsg('Selecciona un vendedor.', 'error'); return; }

    const fechaDate = new Date(this.nuevaFecha + 'T12:00:00');
    if (isNaN(fechaDate.getTime())) { this.mostrarMsg('Fecha inválida.', 'error'); return; }

    this.guardando = true;

    // Snapshot de IDs actuales
    const idsAntes = new Set(this.ventas.map(v => v.idVenta));

    // Paso 1: crear venta base (sin vendedor)
    this.api.crearVentaBase({
      fecha:      fechaDate.getTime(),
      metodoPago: this.nuevaMetodoPago,
    }).subscribe({
      next: () => {
        // Paso 2: recargar ventas para detectar el nuevo ID
        this.api.getVentas().subscribe({
          next: ventasActualizadas => {
            const nuevas = ventasActualizadas.filter(v => !idsAntes.has(v.idVenta));
            const idNuevo = nuevas.length > 0
              ? Math.max(...nuevas.map(v => v.idVenta))
              : Math.max(...ventasActualizadas.map(v => v.idVenta));

            // Paso 3: asignar el vendedor
            this.api.addVendedorAVenta(idNuevo, Number(this.nuevaVendedorId)).subscribe({
              next: () => {
                this.guardando = false;
                this.mostrarMsg('Venta registrada exitosamente.', 'success');
                this.cargar();
                setTimeout(() => this.cerrarModales(), 2000);
              },
              error: () => {
                this.guardando = false;
                this.mostrarMsg('Venta creada pero no se pudo asignar el vendedor.', 'error');
                this.cargar();
              }
            });
          },
          error: () => {
            this.guardando = false;
            this.mostrarMsg('Venta creada pero no se pudo completar. Recarga la página.', 'error');
          }
        });
      },
      error: () => {
        this.guardando = false;
        this.mostrarMsg('Error al registrar la venta.', 'error');
      }
    });
  }

  guardarEdicionVenta(): void {
    if (!this.editVentaId)    { this.mostrarMsg('Selecciona una venta.', 'error'); return; }
    if (!this.editFecha)      { this.mostrarMsg('La fecha es obligatoria.', 'error'); return; }
    if (!this.editMetodoPago) { this.mostrarMsg('Selecciona un método de pago.', 'error'); return; }

    const fechaDate = new Date(this.editFecha + 'T12:00:00');
    if (isNaN(fechaDate.getTime())) { this.mostrarMsg('Fecha inválida.', 'error'); return; }

    this.guardando = true;
    this.api.editarVenta(this.editVentaId, {
      fecha:      fechaDate.getTime(),
      metodoPago: this.editMetodoPago,
    }).subscribe({
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
    if (!v.vendedor) return '—';
    return `${v.vendedor.primerNombre ?? ''} ${v.vendedor.primerApellido ?? ''}`.trim() || '—';
  }
}