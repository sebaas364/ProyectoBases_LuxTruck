import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { ApiService, ProductoDTO } from '../services/api.service';

@Component({
  selector: 'app-inventario',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './inventario.html',
  styleUrls: ['./inventario.css']
})
export class Inventario implements OnInit {

  productos: ProductoDTO[] = [];
  cargando = true;

  mostrarCrear  = false;
  mostrarEditar = false;

  crearNombre = '';
  crearPrecio: number | null = null;
  crearTipo   = '';

  editId: number | null = null;
  editNombre = '';
  editPrecio: number | null = null;
  editTipo   = '';

  mensaje     = '';
  tipoMensaje: 'error' | 'success' | '' = '';
  guardando   = false;

  constructor(private api: ApiService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void { this.cargar(); }

  cargar(): void {
    this.cargando = true;
    this.api.getProductos().subscribe({
      next: data => {
        this.productos = data || [];
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.mostrarMensaje('Error al cargar inventario. ¿Está corriendo el backend?', 'error');
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  mostrarMensaje(texto: string, tipo: 'error' | 'success'): void {
    this.mensaje = texto; this.tipoMensaje = tipo;
    if (tipo === 'success') setTimeout(() => { this.mensaje = ''; this.tipoMensaje = ''; this.cdr.detectChanges(); }, 3000);
    this.cdr.detectChanges();
  }

  abrirCrear(): void {
    this.cerrarModales();
    this.crearNombre = ''; this.crearPrecio = null; this.crearTipo = '';
    this.mostrarCrear = true;
  }

  abrirEditar(p: ProductoDTO): void {
    this.cerrarModales();
    this.editId = p.idProducto; this.editNombre = p.nombre;
    this.editPrecio = p.precioUnitario; this.editTipo = p.tipo;
    this.mostrarEditar = true;
  }

  cerrarModales(): void {
    this.mostrarCrear = false; this.mostrarEditar = false;
    this.mensaje = ''; this.tipoMensaje = '';
  }

  guardarProducto(): void {
    if (!this.crearNombre.trim())               { this.mostrarMensaje('El nombre es obligatorio.', 'error'); return; }
    if (!this.crearPrecio || this.crearPrecio <= 0) { this.mostrarMensaje('El precio debe ser mayor a 0.', 'error'); return; }
    if (!this.crearTipo.trim())                 { this.mostrarMensaje('El tipo es obligatorio.', 'error'); return; }

    this.guardando = true;
    this.api.crearProducto({ nombre: this.crearNombre.trim(), precioUnitario: this.crearPrecio!, tipo: this.crearTipo.trim() }).subscribe({
      next: () => {
        this.guardando = false;
        this.mostrarMensaje('Producto registrado exitosamente.', 'success');
        this.cargar();
        setTimeout(() => this.cerrarModales(), 2000);
      },
      error: () => { this.guardando = false; this.mostrarMensaje('Error al crear el producto.', 'error'); }
    });
  }

  guardarEdicion(): void {
    if (!this.editNombre.trim())               { this.mostrarMensaje('El nombre es obligatorio.', 'error'); return; }
    if (!this.editPrecio || this.editPrecio <= 0) { this.mostrarMensaje('El precio debe ser mayor a 0.', 'error'); return; }
    if (!this.editTipo.trim())                 { this.mostrarMensaje('El tipo es obligatorio.', 'error'); return; }

    this.guardando = true;
    this.api.editarProducto(this.editId!, { nombre: this.editNombre.trim(), precioUnitario: this.editPrecio!, tipo: this.editTipo.trim() }).subscribe({
      next: () => {
        this.guardando = false;
        this.mostrarMensaje('Producto actualizado exitosamente.', 'success');
        this.cargar();
        setTimeout(() => this.cerrarModales(), 2000);
      },
      error: () => { this.guardando = false; this.mostrarMensaje('Error al actualizar el producto.', 'error'); }
    });
  }

  eliminarProducto(id: number): void {
    if (!confirm('¿Deseas eliminar este producto? Esta acción no se puede deshacer.')) return;
    this.api.eliminarProducto(id).subscribe({
      next: () => { this.mostrarMensaje('Producto eliminado.', 'success'); this.cargar(); },
      error: () => this.mostrarMensaje('Error al eliminar. Puede tener relaciones activas.', 'error')
    });
  }
}