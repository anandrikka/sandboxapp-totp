import { Component } from '@angular/core';
import { UserIconComponent } from '../../lib/components/icons/user-icon.component';
import { QrCodeIconComponent } from '../../lib/components/icons/qr-code-icon.component';
import { DeviceIconComponent } from '../../lib/components/icons/device-icon.component';

@Component({
  selector: 'footer.app-footer',
  imports: [
    UserIconComponent,
    QrCodeIconComponent,
    DeviceIconComponent
  ],
  template: `
    <div class="max-w-xl mx-auto px-4 py-2 h-12 flex items-center">
      <div class="flex flex-row justify-around items-center font-medium w-full">
        <button type="button" class="flex flex-col items-center justify-between">
          <span class="fill-a9 w-5 h-5 mb-1">
            <app-user-icon></app-user-icon>
          </span>
<!--          <span class="text-sm">My Profile</span>-->
        </button>
        <button type="button" class="flex flex-col items-center justify-between">
          <span class="fill-foreground w-5 h-5 mb-1">
            <app-qr-code-icon></app-qr-code-icon>
          </span>
<!--          <span class="text-sm">Accounts</span>-->
        </button>
        <button type="button" class="flex flex-col items-center justify-between">
          <span class="fill-foreground w-5 h-5 mb-1">
            <app-device-icon></app-device-icon>
          </span>
<!--          <span class="text-sm">Devices</span>-->
        </button>
      </div>
    </div>
  `,
  styles: ``
})
export class FooterComponent {

}
