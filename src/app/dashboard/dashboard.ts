import { Component, OnInit } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ApiService, MaquinaDTO, ProductoDTO } from '../services/api.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {

  fechaActual = new Date().toLocaleDateString('es-CO', {
    weekday: 'short',
    year: 'numeric',
    month: 'long',
    day: 'numeric'
  });

  nombreUsuario = '';

  cargandoMaquinas = true;
  cargandoProductos = true;
  cargando = true;

  maquinas: MaquinaDTO[] = [];
  productos: ProductoDTO[] = [];

  maquinasTotales = 0;
  maquinasOperativas = 0;
  productosTotales = 0;

  constructor(
    private api: ApiService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const raw = sessionStorage.getItem('usuario');
    if (raw) {
      const u = JSON.parse(raw);
      this.nombreUsuario = `${u.primerNombre} ${u.primerApellido}`;
    }

    this.cargarMaquinas();
    this.cargarProductos();
  }

  // ─────────────────────────────
  // CARGA DE MÁQUINAS
  // ─────────────────────────────
  cargarMaquinas(): void {
    this.cargandoMaquinas = true;
    this.cargando = true;

    this.api.getMaquinas().subscribe({
      next: (data) => {
        this.maquinas = data ?? [];
        this.maquinasTotales = this.maquinas.length;
        this.maquinasOperativas = this.maquinas.filter(m =>
          (m.estadoMaquinadto?.estado ?? '').toUpperCase() === 'OPERATIVA'
        ).length;
        
        this.cargandoMaquinas = false;
        this.validarCarga();
      },
      error: (err) => {
        console.error('Error al cargar máquinas:', err);
        this.maquinas = [];
        this.cargandoMaquinas = false; // Desbloquea la bandera en caso de error
        this.validarCarga();
      }
    });
  }

  // ─────────────────────────────
  // CARGA DE PRODUCTOS
  // ─────────────────────────────
  cargarProductos(): void {
    this.cargandoProductos = true;
    this.cargando = true;

    this.api.getProductos().subscribe({
      next: (data) => {
        this.productos = data ?? [];
        this.productosTotales = this.productos.length;
        
        this.cargandoProductos = false;
        this.validarCarga();
      },
      error: (err) => {
        console.error('Error al cargar productos:', err);
        this.productos = [];
        this.cargandoProductos = false; // Desbloquea la bandera en caso de error
        this.validarCarga();
      }
    });
  }

  // ─────────────────────────────
  // CONTROL DE LOADING GLOBAL
  // ─────────────────────────────
  validarCarga(): void {
    if (!this.cargandoMaquinas && !this.cargandoProductos) {
      this.cargando = false;
    }
  }

  // ─────────────────────────────
  // ESTADOS
  // ─────────────────────────────
  estadoClaseMaquina(estado?: string): string {
    const e = (estado ?? '').toUpperCase();

    if (e === 'OPERATIVA') return 'free';
    if (e === 'MANTENIMIENTO') return 'warning';
    return 'critical';
  }

  // ─────────────────────────────
  // SESIÓN
  // ─────────────────────────────
  cerrarSesion(): void {
    sessionStorage.removeItem('usuario');
    this.router.navigate(['/']);
  }
}