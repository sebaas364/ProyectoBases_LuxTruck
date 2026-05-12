import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  correo = '';
  password = '';
  rol = '';
  mensaje = '';

  constructor(private router: Router) {}

  login() {
    this.router.navigate(['/dashboard']);
  }
}