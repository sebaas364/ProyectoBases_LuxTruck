import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-maquina',
  standalone: true,
  imports: [RouterLink, CommonModule],

  templateUrl: './maquina.html',
  styleUrl: './maquina.css',
})

export class Maquina {

  mostrarCrearMaquina = false;
  mostrarAsignarMaterial = false;
  mostrarEstadoMaquina = false;

  abrirCrearMaquina(){

    this.mostrarCrearMaquina = true;

  }

  abrirAsignarMaterial(){

    this.mostrarAsignarMaterial = true;

  }

  abrirEstadoMaquina(){

    this.mostrarEstadoMaquina = true;

  }

  cerrarModales(){

    this.mostrarCrearMaquina = false;
    this.mostrarAsignarMaterial = false;
    this.mostrarEstadoMaquina = false;

  }

}