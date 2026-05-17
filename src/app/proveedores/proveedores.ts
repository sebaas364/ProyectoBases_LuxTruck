import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-proveedores',
  standalone: true,
  imports: [RouterLink, CommonModule],

  templateUrl: './proveedores.html',
  styleUrl: './proveedores.css',
})

export class Proveedores {

  mostrarCrearProveedor = false;
  mostrarPedidoMaterial = false;
  mostrarEstadoProveedor = false;

  abrirCrearProveedor(){

    this.mostrarCrearProveedor = true;

  }

  abrirPedidoMaterial(){

    this.mostrarPedidoMaterial = true;

  }

  abrirEstadoProveedor(){

    this.mostrarEstadoProveedor = true;

  }

  cerrarModales(){

    this.mostrarCrearProveedor = false;
    this.mostrarPedidoMaterial = false;
    this.mostrarEstadoProveedor = false;

  }

}