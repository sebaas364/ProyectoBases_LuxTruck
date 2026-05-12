import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard {

  fechaActual: string = new Date().toLocaleDateString(
    'es-CO',
    {
      weekday: 'short',
      year: 'numeric',
      month: 'long',
      day: 'numeric'
    }
  );

}