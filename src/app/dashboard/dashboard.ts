import { Component, OnInit } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { catchError, timeout } from 'rxjs/operators';
import { forkJoin, of } from 'rxjs';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {

  fechaActual: string = new Date().toLocaleDateString('es-CO', {
    weekday: 'short',
    year: 'numeric',
    month: 'long',
    day: 'numeric'
  });

  nombreUsuario = '';

  // Datos del endpoint /dashboard/resumen
  maquinasLibres   = 0;
  maquinasTotales  = 0;
  productosEnStock = 0;
  ventasDelMes     = 0;
  stockBajo        = 0;

  // Listas para las tablas
  maquinas:   any[] = [];
  inventario: any[] = [];

  cargando = true;

  constructor(private api: ApiService, private router: Router) {}

  ngOnInit(): void {
    const raw = sessionStorage.getItem('usuario');
    if (raw) {
      const u = JSON.parse(raw);
      this.nombreUsuario = `${u.primerNombre} ${u.primerApellido}`;
    }

    // Carga todo en paralelo; cargando=false cuando terminen todos (con o sin error)
    forkJoin({
      resumen:    this.api.getDashboardResumen().pipe(timeout(5000), catchError(() => of(null))),
      maquinas:   this.api.getMaquinas().pipe(timeout(5000), catchError(() => of([]))),
      inventario: this.api.getInventario().pipe(timeout(5000), catchError(() => of([])))
    }).subscribe({
      next: ({ resumen, maquinas, inventario }) => {
        if (resumen) {
          this.maquinasLibres   = resumen.maquinasLibres;
          this.maquinasTotales  = resumen.maquinasTotales;
          this.productosEnStock = resumen.productosEnStock;
          this.ventasDelMes     = resumen.ventasDelMes;
          this.stockBajo        = resumen.stockBajo;
        }
        this.maquinas   = maquinas  ?? [];
        this.inventario = inventario ?? [];
        this.cargando   = false;
      },
      error: () => {
        // catchError en cada observable evita llegar aquí, pero por si acaso
        this.cargando = false;
      }
    });
  }

  estadoMaquinaClase(estado: string): string {
    const e = estado?.toUpperCase() ?? '';
    if (e === 'OPERATIVA')     return 'free';
    if (e === 'MANTENIMIENTO') return 'warning';
    return 'critical';
  }

  estadoInventarioClase(item: any): string {
    return item.cantidadProducto <= item.stockMinimo ? 'critical' : 'free';
  }

  estadoInventarioLabel(item: any): string {
    return item.cantidadProducto <= item.stockMinimo ? 'Crítico' : 'OK';
  }

  cerrarSesion(): void {
    sessionStorage.removeItem('usuario');
    this.router.navigate(['/']);
  }
}