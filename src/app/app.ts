
import { Component } from '@angular/core';
import { Dashboard } from './dashboard/dashboard';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `<router-outlet />`
})
export class App {

}