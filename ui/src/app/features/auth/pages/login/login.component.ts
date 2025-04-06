import { Component } from '@angular/core';
import { AuthApiService } from '../../auth-api.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { UserApiService } from '../../../users/user-api.service';

enum LoginState {
  INITIATED,
  EMAIL_INVALID ,
  OTP_REQUESTED,
  INVALID_OTP,
  SUCCESS
}

@Component({
  selector: 'app-login',
  imports: [
    FormsModule,
    CommonModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  state: LoginState = LoginState.INITIATED;
  email = '';
  rememberMe = false;
  otp = '';

  constructor(
    private authApiService: AuthApiService,
    private userApiService: UserApiService,
    private router: Router
  ) {}

  async submitLogin() {
    try {
      await this.authApiService.verifyLoginOTP(
        this.email,
        this.otp,
        this.rememberMe
      )
      this.state = LoginState.SUCCESS;
      await this.router.navigate(['/'])
    } catch(err) {
      this.state = LoginState.INVALID_OTP;
    }
  }

  async submitOTP() {
    try {
      await this.authApiService.requestLoginOTP(this.email);
      this.state = LoginState.OTP_REQUESTED;
    } catch(err) {
      this.state = LoginState.EMAIL_INVALID;
    }
  }

  gotoSignup() {
    this.router.navigate(['/signup']);
  }

  protected readonly LoginStage = LoginState;
  protected readonly LoginState = LoginState;
}
