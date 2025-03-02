import { Component, OnInit, signal } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthApiService } from '../../auth-api.service';
import { LoadingState, UserForm } from '../../../../types';
import { Router } from '@angular/router';
import { NgClass } from '@angular/common';
import { ErrorCodes } from '../../../../lib/constants/error-codes';

@Component({
  selector: 'app-signup',
  imports: [
    ReactiveFormsModule,
    NgClass
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent implements OnInit {
  signupState: LoadingState = { status: 'initiate' };

  signupForm = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email])
  });

  constructor(
    private authApiService: AuthApiService,
    private router: Router
  ) {}

  ngOnInit() {
    this.signupForm.valueChanges.subscribe(() => {
      // this.signupState = { status: 'initiate' }
    });
  }

  isControlInvalid(controlName: string) {
    const control = this.signupForm.get(controlName);
    return control?.invalid && (control.touched || control.dirty);
  }

  get isEmailRegistered() {
    return this.signupState.status === 'error' && this.signupState.error.code === ErrorCodes.SIGNUP_EMAIL_REGISTERED;
  }

  get isRegisteredSuccessfully() {
    return this.signupState.status === 'loaded'
  }

  submit() {
    this.signupForm.markAllAsTouched();
    if (this.signupForm.valid) {
      this.authApiService.signup(this.signupForm.value as UserForm)
        .subscribe((state) => {
          this.signupState = state
          if (this.signupState.status === 'loaded') {
            this.signupForm.reset();
          }
        });
    }
  }

  gotoLogin() {
    this.router.navigate(['/login'])
  }

  protected readonly ErrorCodes = ErrorCodes;
}
