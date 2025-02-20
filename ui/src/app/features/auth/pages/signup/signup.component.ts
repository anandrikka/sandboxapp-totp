import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthApiService } from '../../auth-api.service';
import { LoadingState, SignupRequest } from '../../../../types';

@Component({
  selector: 'app-signup',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  submitState?: LoadingState;

  constructor(private authApiService: AuthApiService) {}

  signupForm = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email])
  });

  submit() {
    this.authApiService.signup(this.signupForm.value as SignupRequest).subscribe((state) => {
      console.log(state);
      this.submitState = state;
    });
  }
}
