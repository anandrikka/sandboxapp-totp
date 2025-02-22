export enum ErrorCodes {
  SIGNUP_INVALID_FORM = 'auth.signup.invalidForm',
  SIGNUP_EMAIL_REGISTERED = 'auth.signup.emailRegistered',
  SIGNUP_VERIFY_INVALID_LINK = 'auth.signup.invalidLink',
  SIGNUP_VERIFY_EMAIL_INVALID = 'auth.signup.verify.invalidEmail',
  SIGNUP_VERIFY_TOKEN_INVALID = 'auth.signup.verify.tokenNotFound',
  SIGNUP_VERIFY_TOKEN_ALREADY_USED = 'auth.signup.verify.tokenAlreadyUsed',
  SIGNUP_VERIFY_TOKEN_EXPIRED = 'auth.signup.verify.tokenExpired',
  LOGIN_EMAIL_NOT_FOUND = 'auth.signin.user.notFound',
  LOGIN_VERIFY_OTP_INVALID = 'auth.signin.token.notFound',
  LOGIN_VERIFY_OTP_EXPIRED = 'auth.signin.verify.tokenExpired'
}
