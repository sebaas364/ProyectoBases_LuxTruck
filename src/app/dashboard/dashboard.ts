import { Component, OnInit } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { forkJoin, of } from 'rxjs';
import { catchError, timeout } from 'rxjs/operators';
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

  totalMaquinas = 0;
  maquinasActivas = 0;
  maquinasInactivas = 0;

  totalProductos = 0;
  stockTotal = 0;
  productosCriticos = 0;

  totalTrabajadores = 0;
  totalProveedores = 0;

  maquinas: any[] = [];
  inventario: any[] = [];

  cargando = true;

  nombreUsuario = '';

  constructor(
    private api: ApiService,
    private router: Router
  ) {}

  ngOnInit(): void {

    const raw = sessionStorage.getItem('usuario');

    if (raw) {
      const u = JSON.parse(raw);

      this.nombreUsuario =
        `${u.primerNombre} ${u.primerApellido}`;
    }

    forkJoin({

      admins: this.api.getAdministrativos()
        .pipe(
          timeout(5000),
          catchError(err => {
            console.error('ERROR ADMINISTRATIVOS', err);
            return of([]);
          })
        ),

      operarios: this.api.getOperarios()
        .pipe(
          timeout(5000),
          catchError(err => {
            console.error('ERROR OPERARIOS', err);
            return of([]);
          })
        ),

      vendedores: this.api.getVendedores()
        .pipe(
          timeout(5000),
          catchError(err => {
            console.error('ERROR VENDEDORES', err);
            return of([]);
          })
        ),

      maquinas: this.api.getMaquinas()
        .pipe(
          timeout(5000),
          catchError(err => {
            console.error('ERROR MAQUINAS', err);
            return of([]);
          })
        ),

      proveedores: this.api.getProveedores()
        .pipe(
          timeout(5000),
          catchError(err => {
            console.error('ERROR PROVEEDORES', err);
            return of([]);
          })
        ),

      inventario: this.api.getInventario()
        .pipe(
          timeout(5000),
          catchError(err => {
            console.error('ERROR INVENTARIO', err);
            return of([]);
          })
        )

    }).subscribe({

      next: ({
        admins,
        operarios,
        vendedores,
        maquinas,
        proveedores,
        inventario
      }) => {

        console.log('Dashboard cargado');

        this.totalTrabajadores =
          admins.length +
          operarios.length +
          vendedores.length;

        this.totalProveedores =
          proveedores.length;

        this.maquinas = maquinas;

        this.totalMaquinas =
          maquinas.length;

        this.maquinasActivas =
          maquinas.filter(
            (m: any) =>
              m.estadoMaquinadto?.estado?.toUpperCase() === 'OPERATIVA'
          ).length;

        this.maquinasInactivas =
          this.totalMaquinas - this.maquinasActivas;

        this.inventario = inventario;

        this.totalProductos =
          inventario.length;

        this.stockTotal =
          inventario.reduce(
            (s: number, i: any) =>
              s + (i.cantidadProducto ?? 0),
            0
          );

        this.productosCriticos =
          inventario.filter(
            (i: any) =>
              i.cantidadProducto <= i.stockMinimo
          ).length;

        this.cargando = false;
      },

      error: (err) => {

        console.error('ERROR GENERAL DASHBOARD', err);

        this.cargando = false;
      }
    });
  }

  estadoMaquinaClase(estado: string): string {

    const e = estado?.toUpperCase() ?? '';

    if (e === 'OPERATIVA') {
      return 'free';
    }

    if (e === 'MANTENIMIENTO') {
      return 'warning';
    }

    return 'critical';
  }

  estadoInventarioClase(item: any): string {

    return item.cantidadProducto <= item.stockMinimo
      ? 'critical'
      : 'free';
  }

  estadoInventarioLabel(item: any): string {

    return item.cantidadProducto <= item.stockMinimo
      ? 'Crítico'
      : 'OK';
  }

  cerrarSesion(): void {

    sessionStorage.removeItem('usuario');

    this.router.navigate(['/']);
  }
}