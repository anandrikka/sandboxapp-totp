import { Component, OnInit } from '@angular/core';
import { DeviceApiService } from '../../device-api.service';
import { SettingsHeaderComponent } from '../../../settings/settings-header/settings-header.component';
import { FaIconComponent } from '@fortawesome/angular-fontawesome';
import { faTrash } from '@fortawesome/free-solid-svg-icons/faTrash';

@Component({
  selector: 'app-devices',
  imports: [
    SettingsHeaderComponent,
    FaIconComponent
  ],
  template: `
    <div class="flex flex-col">
      <app-settings-header></app-settings-header>
      @if (devices.length) {
        <div class="flex flex-col">
          @for (device of devices; track device.id) {
            <div class="flex pr-4 py-2 border-b border-border items-center">
              <div class="grow-1 overflow-hidden text-">
                {{ device.deviceName }}
              </div>
              <fa-icon [icon]="faTrash" class="pl-8 cursor-pointer" (click)="deleteDevice(device.id)"></fa-icon>
            </div>
          }
        </div>
      } @else {
        <div class="text-xl text-center ">No devices registered</div>
      }
    </div>
  `
})
export class DevicesComponent implements OnInit {
  devices: any[] = [];
  loading = false;

  constructor(private deviceApiService: DeviceApiService) {}

  ngOnInit() {
    this.fetchDevices();
  }

  fetchDevices() {
    this.deviceApiService.fetchAllDevices().subscribe((response) => {
      this.loading = response.status === 'loading';
      if (response.status === 'loaded') {
        this.devices = response.data as any[];
      }
    })
  }

  deleteDevice(id: string) {
    this.deviceApiService.delete(id).subscribe(() => {
      this.fetchDevices();
    })
  }

  protected readonly faTrash = faTrash;
}
