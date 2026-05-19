import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { ApiService, InventarioDTO, ActualizarInventarioPayload } from '../services/api.service';

@Component({
  selector: 'app-inventario',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './inventario.html',
  styleUrls: ['./inventario.css']
})
export class Inventario implements OnInit {

  inventario: InventarioDTO[] = [];
  cargando = true;

  mostrarCrear    = false;
  mostrarCantidad = false;

  crearNombre      = '';
  crearPrecio: number | null = null;
  crearTipo        = '';
  crearStockMinimo: number | null = null;
  crearCantidad: number | null = null;

  actualizarId: number | null = null;
  actualizarStock: number | null = null;
  actualizarCantidad: number | null = null;

  mensaje     = '';
  tipoMensaje: 'error' | 'success' | '' = '';
  guardando   = false;

  constructor(private api: ApiService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void { this.cargar(); }

  cargar(): void {
    this.cargando = true;
    this.api.getInventario().subscribe({
      next: data => {
        this.inventario = data || [];
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: (err: HttpErrorResponse) => {
        console.error('Error al cargar inventario:', err);
        this.mostrarMensaje('Error al cargar inventario. ¿Está corriendo el backend?', 'error');
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  get totalStock(): number {
    return this.inventario.reduce((s, i) => s + i.cantidadProducto, 0);
  }

  get criticos(): number {
    return this.inventario.filter(i => i.cantidadProducto <= i.stockMinimo).length;
  }

  estadoClase(i: InventarioDTO): string {
    return i.cantidadProducto <= i.stockMinimo ? 'critical' : 'free';
  }

  estadoLabel(i: InventarioDTO): string {
    return i.cantidadProducto <= i.stockMinimo ? 'Crítico' : 'Disponible';
  }

  mostrarMensaje(texto: string, tipo: 'error' | 'success'): void {
    this.mensaje = texto;
    this.tipoMensaje = tipo;
    if (tipo === 'success') {
      setTimeout(() => { this.mensaje = ''; this.tipoMensaje = ''; this.cdr.detectChanges(); }, 3000);
    }
    this.cdr.detectChanges();
  }

  abrirCrear(): void {
    this.cerrarModales();
    this.crearNombre = ''; this.crearPrecio = null; this.crearTipo = '';
    this.crearStockMinimo = null; this.crearCantidad = null;
    this.mostrarCrear = true;
  }

  abrirCantidad(): void {
    this.cerrarModales();
    this.actualizarId = null; this.actualizarStock = null; this.actualizarCantidad = null;
    this.mostrarCantidad = true;
  }

  cerrarModales(): void {
    this.mostrarCrear = false; this.mostrarCantidad = false;
    this.mensaje = ''; this.tipoMensaje = '';
  }

  onSeleccionarProducto(): void {
    if (!this.actualizarId) return;
    const item = this.inventario.find(i => i.idProducto === Number(this.actualizarId));
    if (!item) return;
    this.actualizarStock    = item.stockMinimo;
    this.actualizarCantidad = item.cantidadProducto;
  }

  private validarCrear(): string | null {
    if (!this.crearNombre.trim())                                    return 'El nombre es obligatorio.';
    if (!this.crearPrecio || this.crearPrecio <= 0)                  return 'El precio debe ser mayor a 0.';
    if (!this.crearTipo.trim())                                      return 'El tipo es obligatorio.';
    if (this.crearStockMinimo === null || this.crearStockMinimo < 0) return 'El stock mínimo es obligatorio.';
    if (this.crearCantidad === null || this.crearCantidad < 0)       return 'La cantidad es obligatoria.';
    return null;
  }

  // Flujo 2 pasos:
  // 1) POST /producto/createjson  → crea el producto (ID auto-generado)
  // 2) GET /producto/getall       → detecta el nuevo idProducto por nombre
  // 3) POST /producto/addinventario?idProducto=X&stockMinimo=Y&cantidadProducto=Z
  guardarReferencia(): void {
    const error = this.validarCrear();
    if (error) { this.mostrarMensaje(error, 'error'); return; }

    this.guardando = true;
    this.mensaje   = '';

    // Snapshot de IDs actuales para detectar el nuevo
    const idsAntes = new Set(this.inventario.map(i => i.idProducto));

    this.api.crearProducto({
      nombre:         this.crearNombre.trim(),
      precioUnitario: this.crearPrecio!,
      tipo:           this.crearTipo.trim()
    }).subscribe({
      next: () => {
        // Buscar el nuevo producto por nombre en la lista completa de productos
        this.api.getProductos().subscribe({
          next: productos => {
            // Preferimos el que coincide por nombre y no estaba antes
            const candidatos = productos.filter(p =>
              p.nombre.trim().toLowerCase() === this.crearNombre.trim().toLowerCase() &&
              !idsAntes.has(p.idProducto)
            );
            const idNuevo = candidatos.length > 0
              ? candidatos[candidatos.length - 1].idProducto   // el último si hay varios
              : Math.max(...productos.map(p => p.idProducto)); // fallback: mayor ID

            this.api.asociarInventario(idNuevo, this.crearStockMinimo!, this.crearCantidad!).subscribe({
              next: () => {
                this.guardando = false;
                this.mostrarMensaje('Referencia creada exitosamente.', 'success');
                this.cargar();
                setTimeout(() => this.cerrarModales(), 2000);
              },
              error: (err: HttpErrorResponse) => {
                this.guardando = false;
                if (err.status === 406) {
                  this.mostrarMensaje('El producto ya tiene inventario registrado.', 'error');
                } else {
                  this.mostrarMensaje('Error al asociar el inventario.', 'error');
                }
              }
            });
          },
          error: () => {
            this.guardando = false;
            this.mostrarMensaje('Producto creado pero no se pudo asociar el inventario.', 'error');
          }
        });
      },
      error: (err: HttpErrorResponse) => {
        this.guardando = false;
        console.error('Error al crear producto:', err);
        this.mostrarMensaje('Error al crear el producto.', 'error');
      }
    });
  }

  guardarCantidad(): void {
    if (!this.actualizarId)                                              { this.mostrarMensaje('Selecciona un producto.', 'error'); return; }
    if (this.actualizarCantidad === null || this.actualizarCantidad < 0) { this.mostrarMensaje('La cantidad no puede ser negativa.', 'error'); return; }
    if (this.actualizarStock === null || this.actualizarStock < 0)       { this.mostrarMensaje('El stock mínimo no puede ser negativo.', 'error'); return; }

    const payload: ActualizarInventarioPayload = {
      stockMinimo:      this.actualizarStock,
      cantidadProducto: this.actualizarCantidad
    };

    this.guardando = true;
    this.api.actualizarInventario(Number(this.actualizarId), payload).subscribe({
      next: () => {
        this.mostrarMensaje('Inventario actualizado con éxito.', 'success');
        this.cargar();
        setTimeout(() => this.cerrarModales(), 2000);
      },
      error: (err: HttpErrorResponse) => {
        console.error('Error al actualizar inventario:', err);
        if (err.status === 200 || err.status === 204) {
          this.mostrarMensaje('Inventario actualizado.', 'success');
          this.cargar();
          setTimeout(() => this.cerrarModales(), 2000);
        } else {
          this.mostrarMensaje('Error al actualizar el inventario.', 'error');
        }
      },
      complete: () => { this.guardando = false; this.cdr.detectChanges(); }
    });
  }
}