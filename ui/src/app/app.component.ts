import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { HeaderComponent } from './layouts/header/header.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent],
  template: `
    <div class="flex flex-col h-lvh w-full">
      <header class="app-header"></header>
      <router-outlet />
    </div>
  `
})
export class AppComponent {
  title = 'Authy';

  constructor(private route: ActivatedRoute) {}
}
