import { Component, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { timeout, finalize } from 'rxjs/operators';
import { ApiService, TrabajadorDTO } from '../services/api.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  correo = '';
  password = '';

  mensaje = '';

  tipoMensaje: 'error' | 'success' | '' = '';

  cargando = false;

  constructor(
    private router: Router,
    private api: ApiService,
    private cdr: ChangeDetectorRef
  ) {}

  // ──────────────────────────────────────────
  // Validaciones
  // ──────────────────────────────────────────
  private validar(): string | null {

    if (!this.correo.trim()) {
      return 'El correo es obligatorio.';
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailRegex.test(this.correo)) {
      return 'Ingresa un correo válido.';
    }

    if (!this.password.trim()) {
      return 'La contraseña es obligatoria.';
    }

    if (this.password.length < 4) {
      return 'La contraseña debe tener al menos 4 caracteres.';
    }

    return null;
  }

  // ──────────────────────────────────────────
  // Login principal
  // ──────────────────────────────────────────
  login() {

    this.mensaje = '';
    this.tipoMensaje = '';

    const error = this.validar();

    if (error) {
      this.mensaje = error;
      this.tipoMensaje = 'error';
      return;
    }

    this.cargando = true;

    this.api.login({
      correo: this.correo,
      contrasenia: this.password
    })
    .pipe(

      timeout(5000),

      finalize(() => {

        this.cargando = false;

        // Fuerza actualización visual
        this.cdr.detectChanges();
      })

    )
    .subscribe({

      next: (trabajador: TrabajadorDTO) => {

        this.mensaje = 'Inicio de sesión exitoso';
        this.tipoMensaje = 'success';

        // Guardar usuario
        sessionStorage.setItem(
          'usuario',
          JSON.stringify(trabajador)
        );

        // Navegar
        setTimeout(() => {
          this.router.navigate(['/dashboard']);
        }, 500);
      },

      error: (err: HttpErrorResponse) => {

        console.log('ERROR LOGIN:', err);

        if (err.status === 401) {

          this.mensaje =
            'Correo o contraseña incorrectos.';

        } else if (err.status === 0) {

          this.mensaje =
            'No se puede conectar al servidor.';

        } else {

          this.mensaje =
            'Error inesperado. Intenta de nuevo.';
        }

        this.tipoMensaje = 'error';
      }
    });
  }

  // ──────────────────────────────────────────
  // Enter para iniciar sesión
  // ──────────────────────────────────────────
  onKeyDown(event: KeyboardEvent) {

    if (event.key === 'Enter') {
      this.login();
    }
  }
}