import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterOutlet } from '@angular/router';
import { HeaderComponent } from './layouts/header/header.component';
import { FooterComponent } from './layouts/footer/footer.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, FooterComponent],
  template: `
    <div class="flex flex-col h-lvh w-full">
      <header class="app-header"></header>
      <main class="app-content">
        <div class="max-w-xl px-4 mx-auto">
          <router-outlet />
        </div>
      </main>
      @if (this.router.url.includes('settings')) {
        <footer class="app-footer"></footer>
      }
    </div>
  `
})
export class AppComponent {
  title = 'Authy';

  constructor(protected router: Router) {}
}
