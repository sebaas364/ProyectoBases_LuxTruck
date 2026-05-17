import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-inventario',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './inventario.html',
  styleUrls: ['./inventario.css']
})
export class Inventario {

  mostrarCrear = false;
  mostrarCantidad = false;

  abrirCrear(){
    this.mostrarCrear = true;
  }

  abrirCantidad(){
    this.mostrarCantidad = true;
  }

  cerrarModales(){
    this.mostrarCrear = false;
    this.mostrarCantidad = false;
  }
  validarCantidad(cantidad:number){

  if(cantidad <= 0){
    alert("La cantidad debe ser mayor a 0");
    return false;
  }

  return true;

  }
}