import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthApiService } from '../../auth-api.service';

@Component({
  selector: 'app-signup-verification',
  imports: [],
  templateUrl: './signup-verification.component.html',
  styleUrl: './signup-verification.component.css'
})
export class SignupVerificationComponent implements OnInit {
  inProgress = true;
  errorCode = 'invalid';
  constructor(
    private route: ActivatedRoute,
    private authApiService: AuthApiService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      const { email, activation_code } = params;
      if (!email || !activation_code) {
        throw new Error('Error');
      }
      this.authApiService.signupVerification(email, activation_code).subscribe();
    })
  }
}
