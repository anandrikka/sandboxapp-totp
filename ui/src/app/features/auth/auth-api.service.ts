import { Injectable } from '@angular/core';
import { SignupRequest } from '../../types/Auth';
import { ApiService } from '../../core/services/api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthApiService extends ApiService {

  constructor() {
    super('/api/v1/auth')
  }

  signup(body: SignupRequest) {
    this.httpClient.post(this.buildUrl('/signup'), body).subscribe((response) => {
      console.log(response);
    })
  }

  signupVerification(email: string, token: string) {
    this.httpClient.get(this.buildUrl(`/signup_verification?email=${email}&token=${token}`))
  }
}
