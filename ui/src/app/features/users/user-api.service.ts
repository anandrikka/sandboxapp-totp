import { Injectable } from '@angular/core';
import { ApiService } from '../../core/services/api.service';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserApiService extends ApiService {

  constructor() {
    super("/api/v1/users")
  }

  self() {
    return lastValueFrom(this.httpClient.get(this.buildUrl("/self")))
  }
}
