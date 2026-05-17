import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
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

  constructor(private router: Router, private api: ApiService) {}

  // ──────────────────────────────────────────
  // Hashea la contraseña en SHA-256 (igual que el backend usa DigestUtils.sha256Hex)
  // ──────────────────────────────────────────
  private async sha256(texto: string): Promise<string> {
    const encoder = new TextEncoder();
    const data = encoder.encode(texto);
    const hashBuffer = await crypto.subtle.digest('SHA-256', data);
    const hashArray = Array.from(new Uint8Array(hashBuffer));
    return hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
  }


  private validar(): string | null {
    if (!this.correo.trim()) return 'El correo es obligatorio.';

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(this.correo)) return 'Ingresa un correo válido.';

    if (!this.password.trim()) return 'La contraseña es obligatoria.';
    if (this.password.length < 4) return 'La contraseña debe tener al menos 4 caracteres.';

    return null; // todo bien
  }

  // ──────────────────────────────────────────
  // Login principal
  // ──────────────────────────────────────────
  async login() {
    this.mensaje = '';
    this.tipoMensaje = '';

    const error = this.validar();
    if (error) {
      this.mensaje = error;
      this.tipoMensaje = 'error';
      return;
    }

    this.cargando = true;

    try {
      const contraseniaHash = await this.sha256(this.password);

      this.api.login({ correo: this.correo, contrasenia: contraseniaHash })
        .subscribe({
          next: (trabajador: TrabajadorDTO) => {
            this.cargando = false;
            // Guardamos el usuario en sessionStorage para usarlo en otros módulos
            sessionStorage.setItem('usuario', JSON.stringify(trabajador));
            this.router.navigate(['/dashboard']);
          },
          error: (err: HttpErrorResponse) => {
            this.cargando = false;
            if (err.status === 401) {
              this.mensaje = 'Correo o contraseña incorrectos.';
            } else if (err.status === 0) {
              this.mensaje = 'No se puede conectar al servidor. ¿Está corriendo el backend?';
            } else {
              this.mensaje = 'Error inesperado. Intenta de nuevo.';
            }
            this.tipoMensaje = 'error';
          }
        });

    } catch {
      this.cargando = false;
      this.mensaje = 'Error al procesar la contraseña.';
      this.tipoMensaje = 'error';
    }
  }

  // Permite enviar con Enter
  onKeyDown(event: KeyboardEvent) {
    if (event.key === 'Enter') this.login();
  }
}
