import { Component } from '@angular/core';
import { UserIconComponent } from '../../lib/components/icons/user-icon.component';
import { QrCodeIconComponent } from '../../lib/components/icons/qr-code-icon.component';
import { DeviceIconComponent } from '../../lib/components/icons/device-icon.component';
import { FooterIconComponent } from './footer-icon.component';
import { Router } from '@angular/router';

@Component({
  selector: 'footer.app-footer',
  imports: [
    UserIconComponent,
    QrCodeIconComponent,
    DeviceIconComponent,
    FooterIconComponent
  ],
  template: `
    <div class="max-w-xl mx-auto px-4 py-2 h-12 flex items-center">
      <div class="flex flex-row justify-around items-center font-medium w-full">
        <app-footer-icon
          (iconClick)="gotoSettings('/settings/profile')"
          [activated]="router.url.includes('/profile')">
          <app-user-icon></app-user-icon>
        </app-footer-icon>
        <app-footer-icon
          (iconClick)="gotoSettings('/settings/accounts')"
          [activated]="router.url.includes('/accounts')">
          <app-qr-code-icon></app-qr-code-icon>
        </app-footer-icon>
        <app-footer-icon
          (iconClick)="gotoSettings('/settings/devices')"
          [activated]="router.url.includes('/devices')">
          <app-device-icon></app-device-icon>
        </app-footer-icon>
      </div>
    </div>
  `,
  styles: ``
})
export class FooterComponent {

  constructor(protected router: Router) {}

  gotoSettings(path: string) {
    this.router.navigate([path]);
  }
}
