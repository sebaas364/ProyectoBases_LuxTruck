import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { forkJoin } from 'rxjs';
import {
  ApiService,
  AdministrativoDTO,
  OperarioDTO,
  VendedorDTO,
} from '../services/api.service';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './usuarios.html',
  styleUrl: './usuarios.css'
})
export class Usuarios implements OnInit {

  administrativos: AdministrativoDTO[] = [];
  operarios: OperarioDTO[] = [];
  vendedores: VendedorDTO[] = [];
  cargando = true;

  mostrarCrearTrabajador = false;
  mostrarEditarTrabajador = false;
  mostrarEstadoTrabajador = false;

  tipoTrabajador = '';
  primerNombre = '';
  segundoNombre = '';
  primerApellido = '';
  segundoApellido = '';
  tipoDocumento = '';
  numeroDocumento = '';
  telefono = '';
  correo = '';
  salario: number | null = null;
  fechaIngreso = '';
  contrasenia = '';
  // Operario
  desempenio: number | null = null;
  // Vendedor
  comision: number | null = null;

  tipoEditar = '';
  trabajadorSeleccionadoId: number | null = null;
  editNombre = '';
  editApellido = '';
  editCorreo = '';
  editTelefono = '';
  editSalario: number | null = null;

  tipoEstado = '';
  trabajadorEstadoId: number | null = null;
  nuevoEstadoId: number | null = null;   // 1 = Activo, 2 = Inactivo

  mensaje = '';
  tipoMensaje: 'error' | 'success' | '' = '';
  guardando = false;

  constructor(private api: ApiService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.cargarTodos();
  }

  cargarTodos(): void {
    this.cargando = true;
    forkJoin({
      administrativos: this.api.getAdministrativos(),
      operarios: this.api.getOperarios(),
      vendedores: this.api.getVendedores()
    }).subscribe({
      next: ({ administrativos, operarios, vendedores }) => {
        this.administrativos = administrativos || [];
        this.operarios = operarios || [];
        this.vendedores = vendedores || [];
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.mostrarMensaje('Error al cargar los datos. ¿Está corriendo el backend?', 'error');
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  nombreCompleto(t: AdministrativoDTO | OperarioDTO | VendedorDTO): string {
    const partes = [t.primerNombre, t.segundoNombre, t.primerApellido, t.segundoApellido];
    return partes.filter(Boolean).join(' ');
  }

  estadoClase(t: any): string {
    if (!t) return 'critical';
    // El backend serializa como "estadoTrabajador" (getter getEstadoTrabajador())
    const objEstado = t.estadoTrabajador;
    if (!objEstado || !objEstado.estado) return 'critical';
    return objEstado.estado.toLowerCase() === 'activo' ? 'free' : 'critical';
  }

  get todosLosTrabajadores(): (AdministrativoDTO | OperarioDTO | VendedorDTO)[] {
    return [...this.administrativos, ...this.operarios, ...this.vendedores];
  }

  get fechaMaximaHoy(): string {
    return new Date().toISOString().split('T')[0];
  }

  tipoDeWorker(id: number): 'administrativo' | 'operario' | 'vendedor' | null {
    if (this.administrativos.find(a => a.idPersona === id)) return 'administrativo';
    if (this.operarios.find(o => o.idPersona === id)) return 'operario';
    if (this.vendedores.find(v => v.idPersona === id)) return 'vendedor';
    return null;
  }

  abrirCrearTrabajador(): void {
    this.cerrarModales();
    this.limpiarFormCrear();
    this.mostrarCrearTrabajador = true;
  }

  abrirEditarTrabajador(): void {
    this.cerrarModales();
    this.limpiarFormEditar();
    this.mostrarEditarTrabajador = true;
  }

  abrirEstadoTrabajador(): void {
    this.cerrarModales();
    this.trabajadorEstadoId = null;
    this.nuevoEstadoId = null;
    this.mostrarEstadoTrabajador = true;
  }

  cerrarModales(): void {
    this.mostrarCrearTrabajador = false;
    this.mostrarEditarTrabajador = false;
    this.mostrarEstadoTrabajador = false;
    this.mensaje = '';
    this.tipoMensaje = '';
  }

  limpiarFormCrear(): void {
    this.tipoTrabajador = '';
    this.primerNombre = '';
    this.segundoNombre = '';
    this.primerApellido = '';
    this.segundoApellido = '';
    this.tipoDocumento = '';
    this.numeroDocumento = '';
    this.telefono = '';
    this.correo = '';
    this.salario = null;
    this.fechaIngreso = '';
    this.contrasenia = '';
    this.desempenio = null;
    this.comision = null;
  }

  limpiarFormEditar(): void {
    this.tipoEditar = '';
    this.trabajadorSeleccionadoId = null;
    this.editNombre = '';
    this.editApellido = '';
    this.editCorreo = '';
    this.editTelefono = '';
    this.editSalario = null;
  }

  onSeleccionarParaEditar(): void {
    if (!this.trabajadorSeleccionadoId) return;
    const id = Number(this.trabajadorSeleccionadoId);
    const t = this.todosLosTrabajadores.find(x => x.idPersona === id);
    if (!t) return;
    this.editNombre = t.primerNombre;
    this.editApellido = t.primerApellido;
    this.editCorreo = t.correo;
    this.editTelefono = t.telefono;
    this.editSalario = t.salario;
  }

  private validarCrear(): string | null {
    if (!this.tipoTrabajador) return 'Selecciona el tipo de trabajador.';
    if (!this.primerNombre.trim()) return 'El primer nombre es obligatorio.';
    if (!this.primerApellido.trim()) return 'El primer apellido es obligatorio.';

    const soloLetrasRx = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/;
    if (!soloLetrasRx.test(this.primerNombre) || (this.segundoNombre.trim() && !soloLetrasRx.test(this.segundoNombre))) {
      return 'Los nombres no pueden contener números ni caracteres especiales.';
    }
    if (!soloLetrasRx.test(this.primerApellido) || (this.segundoApellido.trim() && !soloLetrasRx.test(this.segundoApellido))) {
      return 'Los apellidos no pueden contener números ni caracteres especiales.';
    }

    if (!this.tipoDocumento) return 'Selecciona el tipo de documento.';
    if (!this.numeroDocumento.trim()) return 'El número de documento es obligatorio.';
    if (!this.telefono.trim()) return 'El teléfono es obligatorio.';

    const emailRx = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRx.test(this.correo)) return 'Ingresa un correo válido.';
    if (!this.salario || this.salario <= 0) return 'El salario debe ser mayor a 0.';

    if (!this.fechaIngreso) return 'La fecha de ingreso es obligatoria.';
    const fechaSeleccionada = new Date(this.fechaIngreso + 'T00:00:00');
    const fechaActual = new Date();
    fechaActual.setHours(23, 59, 59, 999);
    if (fechaSeleccionada > fechaActual) return 'La fecha de ingreso no puede ser una fecha futura.';

    if (!this.contrasenia || this.contrasenia.length < 4) return 'La contraseña debe tener al menos 4 caracteres.';
    if (this.tipoTrabajador === 'operario' && (this.desempenio === null || this.desempenio < 0)) return 'El desempeño es obligatorio.';
    if (this.tipoTrabajador === 'vendedor' && (this.comision === null || this.comision < 0)) return 'La comisión es obligatoria.';
    return null;
  }

  mostrarMensaje(texto: string, tipo: 'error' | 'success'): void {
    this.mensaje = texto;
    this.tipoMensaje = tipo;
    if (tipo === 'success') {
      setTimeout(() => { this.mensaje = ''; this.tipoMensaje = ''; this.cdr.detectChanges(); }, 3000);
    }
    this.cdr.detectChanges();
  }

  async guardarTrabajador(): Promise<void> {
    const error = this.validarCrear();
    if (error) { this.mostrarMensaje(error, 'error'); return; }

    this.guardando = true;
    this.mensaje = '';

    // IMPORTANTE: el campo debe llamarse "estadoTrabajador" (igual al getter Java getEstadoTrabajador())
    const basePayload = {
      idPersona: 0,
      numeroDocumento: this.numeroDocumento,
      tipoDocumento: this.tipoDocumento,
      primerNombre: this.primerNombre.trim(),
      segundoNombre: this.segundoNombre.trim() || undefined,
      primerApellido: this.primerApellido.trim(),
      segundoApellido: this.segundoApellido.trim() || undefined,
      telefono: this.telefono,
      correo: this.correo,
      salario: this.salario!,
      fechaIngreso: this.fechaIngreso,
      contrasenia: this.contrasenia,
      estadoTrabajador: { idEstadoTrabajador: 1, estado: 'Activo' }
    };

    let obs$;
    if (this.tipoTrabajador === 'vendedor') {
      obs$ = this.api.crearVendedor({
        ...basePayload,
        comision: this.comision ?? 0
      });
    } else if (this.tipoTrabajador === 'administrativo') {
      obs$ = this.api.crearAdministrativo(basePayload);
    } else {
      obs$ = this.api.crearOperario({
        ...basePayload,
        desempenio: this.desempenio ?? 0
      });
    }

    obs$.subscribe({
      next: () => {
        this.guardando = false;
        this.mostrarMensaje('Trabajador creado exitosamente.', 'success');
        this.limpiarFormCrear();
        this.cargarTodos();
        setTimeout(() => this.cerrarModales(), 2000);
      },
      error: (err: HttpErrorResponse) => {
        this.guardando = false;
        console.error('Error devuelto por el servidor:', err);
        this.mostrarMensaje('Error al guardar el trabajador. Verifica los datos en consola.', 'error');
      }
    });
  }

  guardarEdicion(): void {
    if (!this.trabajadorSeleccionadoId) { this.mostrarMensaje('Selecciona un trabajador.', 'error'); return; }
    if (!this.editNombre.trim()) { this.mostrarMensaje('El nombre es obligatorio.', 'error'); return; }
    const emailRx = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRx.test(this.editCorreo)) { this.mostrarMensaje('Ingresa un correo válido.', 'error'); return; }

    const id = Number(this.trabajadorSeleccionadoId);
    const tipo = this.tipoDeWorker(id);
    const payload = {
      primerNombre: this.editNombre,
      primerApellido: this.editApellido,
      correo: this.editCorreo,
      telefono: this.editTelefono,
      salario: this.editSalario ?? undefined
    };

    this.guardando = true;
    let obs$;
    if (tipo === 'administrativo') obs$ = this.api.editarAdministrativo(id, payload);
    else if (tipo === 'operario') obs$ = this.api.editarOperario(id, payload);
    else obs$ = this.api.editarVendedor(id, payload);

    obs$.subscribe({
      next: () => {
        this.guardando = false;
        this.mostrarMensaje('Trabajador actualizado.', 'success');
        this.cargarTodos();
        setTimeout(() => this.cerrarModales(), 2000);
      },
      error: () => {
        this.guardando = false;
        this.mostrarMensaje('Error al actualizar.', 'error');
      }
    });
  }

  guardarEstado(): void {
    if (!this.trabajadorEstadoId) { this.mostrarMensaje('Selecciona un trabajador.', 'error'); return; }
    if (!this.nuevoEstadoId) { this.mostrarMensaje('Selecciona el nuevo estado.', 'error'); return; }

    const id = Number(this.trabajadorEstadoId);
    const tipo = this.tipoDeWorker(id);
    const idEstado = Number(this.nuevoEstadoId);

    this.guardando = true;
    let obs$;
    if (tipo === 'administrativo') obs$ = this.api.cambiarEstadoAdministrativo(id, idEstado);
    else if (tipo === 'operario') obs$ = this.api.cambiarEstadoOperario(id, idEstado);
    else obs$ = this.api.cambiarEstadoVendedor(id, idEstado);

    obs$.subscribe({
      next: () => {
        this.mostrarMensaje('Estado actualizado con éxito.', 'success');
        this.cargarTodos();
        setTimeout(() => this.cerrarModales(), 2000);
      },
      error: (err) => {
        console.error('Error al cambiar estado:', err);
        if (err.status === 200 || err.status === 204) {
          this.mostrarMensaje('Estado actualizado.', 'success');
          this.cargarTodos();
          setTimeout(() => this.cerrarModales(), 2000);
        } else {
          this.mostrarMensaje('Error al cambiar el estado del trabajador.', 'error');
        }
      },
      complete: () => {
        this.guardando = false;
        this.cdr.detectChanges();
      }
    });
  }
}