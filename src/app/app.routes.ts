import { Routes } from '@angular/router';
import { Login } from './login/login';
import { Dashboard } from './dashboard/dashboard';
import { Usuarios } from './usuarios/usuarios';
import { Inventario } from './inventario/inventario';
import { Proveedores } from './proveedores/proveedores';
import { Maquina } from './maquina/maquina';
import { authGuard } from './services/auth.guard';
import { Ventas } from './ventas/ventas';

export const routes: Routes = [
  { path: '', component: Login },
  { path: 'dashboard',  component: Dashboard,   canActivate: [authGuard] },
  { path: 'usuarios',   component: Usuarios,    canActivate: [authGuard] },
  { path: 'inventario', component: Inventario,  canActivate: [authGuard] },
  { path: 'proveedores',component: Proveedores, canActivate: [authGuard] },
  { path: 'ventas',     component: Ventas,      canActivate: [authGuard] },
  { path: 'maquinas',   component: Maquina,     canActivate: [authGuard] },
  { path: '**', redirectTo: '' }
];