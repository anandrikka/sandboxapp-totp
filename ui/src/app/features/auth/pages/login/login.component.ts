import { Component } from '@angular/core';
import { AuthApiService } from '../../auth-api.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

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
  email = '';
  rememberMe = false;
  otp = '';
  otpRequested = false;

  constructor(
    private authApiService: AuthApiService,
    private router: Router
  ) {}

  async submitLogin() {
    try {
      await this.authApiService.verifyLoginOTP(
        this.email,
        this.otp,
        this.rememberMe
      )
      await this.router.navigate(['/'])
    } catch(err) {
      console.log(err);
    }
  }

  async submitOTP() {
    try {
      await this.authApiService.requestLoginOTP(this.email);
      this.otpRequested = true;
    } catch(err) {
      console.log(err);
    }
  }
}
