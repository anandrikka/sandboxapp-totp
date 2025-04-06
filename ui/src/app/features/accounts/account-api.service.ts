import { Injectable } from '@angular/core';
import { ApiService } from '../../core/services/api.service';

@Injectable({
  providedIn: 'root'
})
export class AccountApiService extends ApiService {

  constructor() {
    super("/api/v1/accounts")
  }

  getAllAccounts() {
    return this.requestState(this.httpClient.get(this.path))
  }

  getOtps(id: string) {
    return this.httpClient.get(this.buildUrl(`/${id}/otps`))
  }

  create(account: Record<string, any>) {
    return this.httpClient.post(this.path, account)
  }
}
