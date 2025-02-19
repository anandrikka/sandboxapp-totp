import { Component, OnInit } from '@angular/core';
import { DeviceApiService } from '../../device-api.service';

@Component({
  selector: 'app-devices',
  imports: [],
  templateUrl: './devices.component.html',
  styleUrl: './devices.component.css'
})
export class DevicesComponent implements OnInit {
  devices: any[] = [];
  loading = false;

  constructor(private deviceApiService: DeviceApiService) {}

  ngOnInit() {
    this.deviceApiService.fetchAllDevices().subscribe((response) => {
      this.loading = response.state === 'loading';
      if (response.state === 'loaded') {
        this.devices = response.data as any[];
      }
    })
  }
}
