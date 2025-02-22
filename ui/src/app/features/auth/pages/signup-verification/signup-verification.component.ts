import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthApiService } from '../../auth-api.service';
import { LoadingState } from '../../../../types';
import { ErrorCodes } from '../../../../lib/constants/error-codes';

const invalidLinkErrors = [
  ErrorCodes.SIGNUP_VERIFY_INVALID_LINK,
  ErrorCodes.SIGNUP_VERIFY_EMAIL_INVALID,
  ErrorCodes.SIGNUP_VERIFY_TOKEN_INVALID,
  ErrorCodes.SIGNUP_VERIFY_TOKEN_ALREADY_USED
]

@Component({
  selector: 'app-signup-verification',
  imports: [],
  template: `
    @if (isInvalidLink || isLinkExpired) {
      <div class="bg-danger text-danger-foreground p-4">
        @if (isInvalidLink) {
          <p>
            Link is invalid, please try again by requesting a new activation link!
          </p>
        } @else {
          <p>
            Link expired, request a new link!
          </p>
        }
      </div>
    }
    @if (isActivated) {
      <div class="bg-success text-success-foreground p-4">
        <p>Account activated successfully! <a href="/login" class="underline">Login here</a></p>
      </div>
    }
  `,
})
export class SignupVerificationComponent implements OnInit {
  activationState: LoadingState = { status: 'initiate' };
  constructor(
    private route: ActivatedRoute,
    private authApiService: AuthApiService
  ) {}

  get isInvalidLink() {
    return this.activationState.status === 'error' &&  invalidLinkErrors.includes(<ErrorCodes>this.activationState.error.code)
  }

  get isActivated() {
    return this.activationState.status === 'loaded';
  }

  get isLinkExpired() {
    return this.activationState.status === 'error' &&  this.activationState.error.code === ErrorCodes.SIGNUP_VERIFY_TOKEN_EXPIRED;
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      const { email, activation_code } = params;
      if (!email || !activation_code) {
        this.activationState = { status: 'error', error: { code: ErrorCodes.SIGNUP_VERIFY_INVALID_LINK } };
        return;
      }
      this.authApiService.signupVerification(email, activation_code).subscribe((state) => {
        this.activationState = state;
      });
    })
  }

  protected readonly JSON = JSON;
}
