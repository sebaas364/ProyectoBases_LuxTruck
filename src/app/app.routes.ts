import { Routes } from '@angular/router';
import { Login } from './login/login';
import { Dashboard } from './dashboard/dashboard';
import { Usuarios } from './usuarios/usuarios';
import { Inventario } from './inventario/inventario';
import { Proveedores } from './proveedores/proveedores';
import { Maquina } from './maquina/maquina';

export const routes: Routes = [
  { path: '', component: Login },
  { path: 'dashboard', component: Dashboard },
  { path: 'usuarios', component: Usuarios },
  {path: 'inventario', component: Inventario},
  {path: 'proveedores', component: Proveedores },
  { path: 'maquinas', component: Maquina }
];