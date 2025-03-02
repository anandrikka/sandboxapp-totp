import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-settings-header',
  imports: [],
  template: `
    <div class="py-4 grid grid-cols-3 gap-1">
      <span class="cursor-pointer" (click)="exit()">Exit</span>
      <span class="text-center">{{ title }}</span>
      <ng-content select="app-settings-header-right"></ng-content>
    </div>
  `,
  styles: ``
})
export class SettingsHeaderComponent {
  constructor(private router: Router) {}

  exit() {
    this.router.navigate(['']);
  }

  get title() {
    const url = this.router.url;
    if (url.includes('/settings/profile')) {
      return 'My Account';
    } else if (url.includes('/settings/accounts')) {
      return 'Accounts';
    } else if (url.includes('/settings/devices')) {
      return 'Devices';
    }
    return ' ';
  }
}
