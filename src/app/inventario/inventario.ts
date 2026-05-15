import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
@Component({
  selector: 'app-inventario',
  imports: [RouterLink, BrowserModule, FormsModule],

  templateUrl: './inventario.html',
  styleUrl: './inventario.css',


})
export class Inventario {

}
