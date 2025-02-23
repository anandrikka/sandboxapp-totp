import { Component } from '@angular/core';
import { GearIconComponent } from '../../lib/components/icons/gear-icon.component';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'header.app-header',
  imports: [
    GearIconComponent
  ],
  template: `
    <div class="max-w-xl flex flex-wrap justify-between items-center mx-auto h-12 px-4">
      <span class="font-bold text-lg">Authy</span>
      @if (authService.loggedIn()) {
        <app-gear-icon (iconClick)="onSettingsClick()"></app-gear-icon>
      }
    </div>
  `
})
export class HeaderComponent {
  constructor(
    protected authService: AuthService,
    private router: Router
  ) {
  }

  onSettingsClick() {
    this.router.navigate(['/settings']);
  }
}
