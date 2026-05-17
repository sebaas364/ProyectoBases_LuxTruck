import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './usuarios.html',
  styleUrl: './usuarios.css'
})
export class Usuarios {

  // ======================
  // MODALES
  // ======================
  mostrarCrearTrabajador = false;
  mostrarEditarTrabajador = false;
  mostrarEstadoTrabajador = false;

  // ======================
  // FORMULARIO
  // ======================
  tipoTrabajador = '';
  primerNombre = '';
  primerApellido = '';

  // ======================
  // MENSAJES
  // ======================
  mensaje = '';
  tipoMensaje: 'error' | 'success' | '' = '';

  // ======================
  // CASCARÓN (opcional)
  // ======================
  trabajadores: any[] = [];

  // ======================
  // MODALES
  // ======================
  abrirCrearTrabajador() {
    this.cerrarModales();
    this.mostrarCrearTrabajador = true;
  }

  abrirEditarTrabajador() {
    this.cerrarModales();
    this.mostrarEditarTrabajador = true;
  }

  abrirEstadoTrabajador() {
    this.cerrarModales();
    this.mostrarEstadoTrabajador = true;
  }

  cerrarModales() {
    this.mostrarCrearTrabajador = false;
    this.mostrarEditarTrabajador = false;
    this.mostrarEstadoTrabajador = false;

    this.mensaje = '';
    this.tipoMensaje = '';
  }

  // ======================
  // MENSAJES BONITOS
  // ======================
  mostrarMensaje(texto: string, tipo: 'error' | 'success') {
    this.mensaje = texto;
    this.tipoMensaje = tipo;

    setTimeout(() => {
      this.mensaje = '';
      this.tipoMensaje = '';
    }, 3000);
  }

  // ======================
  // GUARDAR TRABAJADOR
  // ======================
  guardarTrabajador() {

    this.mensaje = '';
    this.tipoMensaje = '';

    if (!this.tipoTrabajador.trim()) {
      this.mostrarMensaje('Debe seleccionar un tipo de trabajador', 'error');
      return;
    }

    if (!this.primerNombre.trim()) {
      this.mostrarMensaje('El primer nombre es obligatorio', 'error');
      return;
    }

    if (!this.primerApellido.trim()) {
      this.mostrarMensaje('El primer apellido es obligatorio', 'error');
      return;
    }

    const nuevoTrabajador = {
      tipo: this.tipoTrabajador,
      nombre: this.primerNombre,
      apellido: this.primerApellido
    };

    this.trabajadores.push(nuevoTrabajador);

    this.mostrarMensaje('Trabajador guardado correctamente', 'success');

    this.tipoTrabajador = '';
    this.primerNombre = '';
    this.primerApellido = '';
  }
}