import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService, MaquinaDTO, CrearMaquinaPayload } from '../services/api.service';

@Component({
  selector: 'app-maquina',
  standalone: true,
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './maquina.html',
  styleUrl: './maquina.css',
})
export class Maquina implements OnInit {

  maquinas: MaquinaDTO[] = [];
  cargando = true;

  // Modales
  mostrarCrearMaquina    = false;
  mostrarAsignarMaterial = false;
  mostrarEstadoMaquina   = false;
  mostrarEditarMaquina   = false;

  // Formulario Crear
  nuevoNumeroSerie = '';
  nuevoTipo        = '';

  // Formulario Editar
  editId: number | null = null;
  editNumeroSerie  = '';
  editTipo         = '';

  // Formulario Asignar Material (llama al endpoint addmaterial del backend)
  asignarMaquinaId: number | null  = null;
  asignarMaterialId: number | null = null;
  asignarDescripcion = '';

  // Formulario Estado
  estadoMaquinaId: number | null = null;
  // 1=OPERATIVA, 2=MANTENIMIENTO, 3=FUERA_SERVICIO
  nuevoEstadoId: number | null   = null;

  mensaje     = '';
  tipoMensaje: 'error' | 'success' | '' = '';
  guardando   = false;

  get activas():   number { return this.maquinas.filter(m => m.estadoMaquinadto?.estado?.toUpperCase() === 'OPERATIVA').length; }
  get inactivas(): number { return this.maquinas.length - this.activas; }

  constructor(private api: ApiService) {}

  ngOnInit(): void { this.cargar(); }

  cargar(): void {
    this.cargando = true;
    this.api.getMaquinas().subscribe({
      next: data => { this.maquinas = data; this.cargando = false; },
      error: () => { this.mostrarMsg('Error al cargar máquinas.', 'error'); this.cargando = false; }
    });
  }

  estadoClase(m: MaquinaDTO): string {
    const e = m.estadoMaquinadto?.estado?.toUpperCase() ?? '';
    if (e === 'OPERATIVA')     return 'free';
    if (e === 'MANTENIMIENTO') return 'warning';
    return 'critical';
  }

  mostrarMsg(texto: string, tipo: 'error' | 'success'): void {
    this.mensaje = texto; this.tipoMensaje = tipo;
    if (tipo === 'success') setTimeout(() => { this.mensaje = ''; this.tipoMensaje = ''; }, 3000);
  }

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
    this.estadoMaquinaId = null; this.nuevoEstadoId = null;
    this.mostrarEstadoMaquina = true;
  }

  abrirEditarMaquina(m: MaquinaDTO): void {
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

  guardarMaquina(): void {
    if (!this.nuevoNumeroSerie.trim()) { this.mostrarMsg('El número de serie es obligatorio.', 'error'); return; }
    if (!this.nuevoTipo)               { this.mostrarMsg('Selecciona el tipo de máquina.', 'error'); return; }

    const payload: CrearMaquinaPayload = {
      numeroSerie: this.nuevoNumeroSerie,
      tipo: this.nuevoTipo,
      estadoMaquinadto: { idEstadoMaquina: 1, estado: 'OPERATIVA' }
    };

    this.guardando = true;
    this.api.crearMaquina(payload).subscribe({
      next: () => {
        this.guardando = false;
        this.mostrarMsg('Máquina creada correctamente.', 'success');
        this.cargar();
        setTimeout(() => this.cerrarModales(), 1800);
      },
      error: () => { this.guardando = false; this.mostrarMsg('Error al crear. ¿El número de serie ya existe?', 'error'); }
    });
  }

  guardarEdicion(): void {
    if (!this.editNumeroSerie.trim()) { this.mostrarMsg('El número de serie es obligatorio.', 'error'); return; }

    this.guardando = true;
    this.api.editarMaquina(this.editId!, {
      numeroSerie: this.editNumeroSerie,
      tipo: this.editTipo
    }).subscribe({
      next: () => {
        this.guardando = false;
        this.mostrarMsg('Máquina actualizada.', 'success');
        this.cargar();
        setTimeout(() => this.cerrarModales(), 1800);
      },
      error: () => { this.guardando = false; this.mostrarMsg('Error al actualizar.', 'error'); }
    });
  }

  guardarEstado(): void {
    if (!this.estadoMaquinaId) { this.mostrarMsg('Selecciona una máquina.', 'error'); return; }
    if (!this.nuevoEstadoId)   { this.mostrarMsg('Selecciona el nuevo estado.', 'error'); return; }

    this.guardando = true;
    this.api.cambiarEstadoMaquina(Number(this.estadoMaquinaId), Number(this.nuevoEstadoId)).subscribe({
      next: () => {
        this.guardando = false;
        this.mostrarMsg('Estado actualizado.', 'success');
        this.cargar();
        setTimeout(() => this.cerrarModales(), 1800);
      },
      error: () => { this.guardando = false; this.mostrarMsg('Error al cambiar el estado.', 'error'); }
    });
  }

  // Asignar material usa el endpoint addmaterial del backend
  guardarAsignacion(): void {
    if (!this.asignarMaquinaId)  { this.mostrarMsg('Selecciona una máquina.', 'error'); return; }
    if (!this.asignarMaterialId) { this.mostrarMsg('Selecciona un material.', 'error'); return; }

    this.guardando = true;
    // El backend espera: /maquina/addmaterial?idMaquina=X&idMaterial=Y&materialUsado=Z
    const url = `http://localhost:8084/maquina/addmaterial?idMaquina=${this.asignarMaquinaId}&idMaterial=${this.asignarMaterialId}&materialUsado=${encodeURIComponent(this.asignarDescripcion || 'Asignado')}`;
    // Usamos HttpClient directamente a través del servicio no expuesto — llamada directa
    fetch(url, { method: 'POST' })
      .then(r => {
        this.guardando = false;
        if (r.ok) {
          this.mostrarMsg('Material asignado correctamente.', 'success');
          this.cargar();
          setTimeout(() => this.cerrarModales(), 1800);
        } else {
          this.mostrarMsg('Error al asignar el material.', 'error');
        }
      })
      .catch(() => { this.guardando = false; this.mostrarMsg('Error de conexión.', 'error'); });
  }

  eliminarMaquina(id: number): void {
    if (!confirm('¿Seguro que deseas eliminar esta máquina?')) return;
    this.api.eliminarMaquina(id).subscribe({
      next: () => { this.mostrarMsg('Máquina eliminada.', 'success'); this.cargar(); },
      error: () => this.mostrarMsg('Error al eliminar. Puede tener relaciones activas.', 'error')
    });
  }
}