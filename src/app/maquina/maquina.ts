import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

// ── Interfaces alineadas con el MER ──────────────────
export type EstadoMaquina = 'Activa' | 'Inactiva' | 'Mantenimiento';

export interface MaquinaItem {
  idMaquina: number;
  numeroSerie: string;
  tipo: string;
  estado: EstadoMaquina;
  materialUsado?: string;   // relación usar (M:N)
}

export interface ProduccionItem {
  idMaquina: number;
  nombreMaquina: string;
  idProducto: number;
  nombreProducto: string;
  tiempoProduccion: string;  // campo del MER en la rel. producir
  precioUnitario: number;
}

@Component({
  selector: 'app-maquina',
  standalone: true,
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './maquina.html',
  styleUrl: './maquina.css',
})
export class Maquina implements OnInit {

  // ── Datos de muestra (se reemplazan al conectar el back) ──
  maquinas: MaquinaItem[] = [
    { idMaquina: 1, numeroSerie: 'SN-XENON-001', tipo: 'Inyectora',     estado: 'Activa',    materialUsado: 'Bombillo Xenon D2S' },
    { idMaquina: 2, numeroSerie: 'SN-XENON-002', tipo: 'Troqueladora',  estado: 'Inactiva',  materialUsado: 'Cable Arnés' },
    { idMaquina: 3, numeroSerie: 'SN-XENON-003', tipo: 'Ensambladora',  estado: 'Activa',    materialUsado: '' },
  ];

  produccion: ProduccionItem[] = [
    { idMaquina: 1, nombreMaquina: 'MAQ-01', idProducto: 1, nombreProducto: 'Kit Xenon H7',  tiempoProduccion: '20 min', precioUnitario: 20000 },
    { idMaquina: 2, nombreMaquina: 'MAQ-02', idProducto: 2, nombreProducto: 'Bombillo D2S',  tiempoProduccion: '15 min', precioUnitario: 15000 },
  ];

  // Materiales disponibles para asignación (del módulo Inventario)
  // TODO: cargar desde /material/getall
  materialesDisponibles = [
    { idMaterial: 1, nombre: 'Bombillo Xenon D2S' },
    { idMaterial: 2, nombre: 'Cable Arnés' },
    { idMaterial: 3, nombre: 'Kit Xenon H7' },
  ];

  // ── Modales ──────────────────────────────────────────
  mostrarCrearMaquina    = false;
  mostrarAsignarMaterial = false;
  mostrarEstadoMaquina   = false;
  mostrarEditarMaquina   = false;

  // ── Formulario Crear ──────────────────────────────────
  nuevoNumeroSerie = '';
  nuevoTipo        = '';

  // ── Formulario Editar ─────────────────────────────────
  editId: number | null  = null;
  editNumeroSerie  = '';
  editTipo         = '';

  // ── Formulario Asignar Material ───────────────────────
  asignarMaquinaId: number | null   = null;
  asignarMaterialId: number | null  = null;
  asignarDescripcion = '';

  // ── Formulario Estado ─────────────────────────────────
  estadoMaquinaId: number | null    = null;
  nuevoEstado: EstadoMaquina | ''   = '';

  // ── Mensajes ──────────────────────────────────────────
  mensaje     = '';
  tipoMensaje: 'error' | 'success' | '' = '';
  guardando   = false;

  // ── Getters para cards ────────────────────────────────
  get activas():      number { return this.maquinas.filter(m => m.estado === 'Activa').length; }
  get inactivas():    number { return this.maquinas.filter(m => m.estado !== 'Activa').length; }
  get totalProductos(): number { return this.produccion.length; }

  ngOnInit(): void {
    // TODO: this.api.getMaquinas().subscribe(data => this.maquinas = data)
    // TODO: this.api.getProduccion().subscribe(data => this.produccion = data)
  }

  // ── Helpers ───────────────────────────────────────────
  estadoClase(m: MaquinaItem): string {
    if (m.estado === 'Activa') return 'free';
    if (m.estado === 'Mantenimiento') return 'warning';
    return 'critical';
  }

  maquinaLabel(id: number): string {
    const m = this.maquinas.find(x => x.idMaquina === id);
    return m ? `MAQ-0${m.idMaquina} — ${m.tipo}` : '';
  }

  mostrarMsg(texto: string, tipo: 'error' | 'success'): void {
    this.mensaje = texto; this.tipoMensaje = tipo;
    if (tipo === 'success') setTimeout(() => { this.mensaje = ''; this.tipoMensaje = ''; }, 3000);
  }

  // ── Abrir modales ─────────────────────────────────────
  abrirCrearMaquina(): void {
    this.cerrarModales();
    this.nuevoNumeroSerie = ''; this.nuevoTipo = '';
    this.mostrarCrearMaquina = true;
  }

  abrirAsignarMaterial(): void {
    this.cerrarModales();
    this.asignarMaquinaId = null; this.asignarMaterialId = null; this.asignarDescripcion = '';
    this.mostrarAsignarMaterial = true;
  }

  abrirEstadoMaquina(): void {
    this.cerrarModales();
    this.estadoMaquinaId = null; this.nuevoEstado = '';
    this.mostrarEstadoMaquina = true;
  }

  abrirEditarMaquina(m: MaquinaItem): void {
    this.cerrarModales();
    this.editId = m.idMaquina;
    this.editNumeroSerie = m.numeroSerie;
    this.editTipo = m.tipo;
    this.mostrarEditarMaquina = true;
  }

  cerrarModales(): void {
    this.mostrarCrearMaquina = false; this.mostrarAsignarMaterial = false;
    this.mostrarEstadoMaquina = false; this.mostrarEditarMaquina = false;
    this.mensaje = ''; this.tipoMensaje = '';
  }

  // ── CRUD ──────────────────────────────────────────────
  guardarMaquina(): void {
    if (!this.nuevoNumeroSerie.trim()) { this.mostrarMsg('El número de serie es obligatorio.', 'error'); return; }
    if (!this.nuevoTipo)               { this.mostrarMsg('Selecciona el tipo de máquina.', 'error'); return; }

    const existe = this.maquinas.find(m => m.numeroSerie.toLowerCase() === this.nuevoNumeroSerie.toLowerCase());
    if (existe) { this.mostrarMsg('Ya existe una máquina con ese número de serie.', 'error'); return; }

    // TODO: this.api.crearMaquina({...}).subscribe(...)
    this.maquinas.push({
      idMaquina:    this.maquinas.length + 1,
      numeroSerie:  this.nuevoNumeroSerie,
      tipo:         this.nuevoTipo,
      estado:       'Activa',
      materialUsado: '',
    });
    this.mostrarMsg('Máquina creada correctamente.', 'success');
    setTimeout(() => this.cerrarModales(), 1800);
  }

  guardarEdicion(): void {
    if (!this.editNumeroSerie.trim()) { this.mostrarMsg('El número de serie es obligatorio.', 'error'); return; }

    // TODO: this.api.editarMaquina(this.editId!, {...}).subscribe(...)
    const m = this.maquinas.find(x => x.idMaquina === this.editId);
    if (m) { m.numeroSerie = this.editNumeroSerie; m.tipo = this.editTipo || m.tipo; }
    this.mostrarMsg('Máquina actualizada.', 'success');
    setTimeout(() => this.cerrarModales(), 1800);
  }

  guardarAsignacion(): void {
    if (!this.asignarMaquinaId)   { this.mostrarMsg('Selecciona una máquina.', 'error'); return; }
    if (!this.asignarMaterialId)  { this.mostrarMsg('Selecciona un material.', 'error'); return; }

    // TODO: this.api.asignarMaterialMaquina(asignarMaquinaId, asignarMaterialId, descripcion).subscribe(...)
    const m   = this.maquinas.find(x => x.idMaquina === Number(this.asignarMaquinaId));
    const mat = this.materialesDisponibles.find(x => x.idMaterial === Number(this.asignarMaterialId));
    if (m && mat) m.materialUsado = mat.nombre;
    this.mostrarMsg('Material asignado correctamente.', 'success');
    setTimeout(() => this.cerrarModales(), 1800);
  }

  guardarEstado(): void {
    if (!this.estadoMaquinaId) { this.mostrarMsg('Selecciona una máquina.', 'error'); return; }
    if (!this.nuevoEstado)     { this.mostrarMsg('Selecciona el nuevo estado.', 'error'); return; }

    // TODO: this.api.cambiarEstadoMaquina(estadoMaquinaId, idEstado).subscribe(...)
    const m = this.maquinas.find(x => x.idMaquina === Number(this.estadoMaquinaId));
    if (m) m.estado = this.nuevoEstado as EstadoMaquina;
    this.mostrarMsg('Estado actualizado.', 'success');
    setTimeout(() => this.cerrarModales(), 1800);
  }

  eliminarMaquina(id: number): void {
    if (!confirm('¿Seguro que deseas eliminar esta máquina?')) return;
    // TODO: this.api.eliminarMaquina(id).subscribe(...)
    this.maquinas = this.maquinas.filter(m => m.idMaquina !== id);
  }
}