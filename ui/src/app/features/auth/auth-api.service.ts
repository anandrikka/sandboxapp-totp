import { Injectable } from '@angular/core';
import { ApiService } from '../../core/services/api.service';
import { SignupRequest } from '../../types/auth';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthApiService extends ApiService {

  constructor() {
    super('/api/v1/auth')
  }

  signup(body: SignupRequest) {
    return this.requestState(this.httpClient.post(this.buildUrl('/signup'), body));
  }

  signupVerification(email: string, token: string) {
    return this.requestState(
      this.httpClient.get(this.buildUrl(`/signup_verification?email=${email}&token=${token}`))
    );
  }

  requestLoginOTP(email: string) {
    return lastValueFrom(
      this.httpClient.post(this.buildUrl('/signin_token'), { email })
    );
  }

  verifyLoginOTP(email: string, otp: string, rememberMe: boolean) {
    return lastValueFrom(
      this.httpClient.post(this.buildUrl('/verify_signin'), {
        email,
        otp,
        rememberMe
      })
    );
  }
}
