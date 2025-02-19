import { Injectable } from '@angular/core';
import { ApiService } from '../../core/services/api.service';

@Injectable({
  providedIn: 'root'
})
export class DeviceApiService extends ApiService {

  constructor() {
    super("/api/v1/devices")
  }

  fetchAllDevices() {
    return this.requestState(this.httpClient.get(this.path))
  }
}
