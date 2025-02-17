import { Routes } from '@angular/router';
import { AccountsComponent } from './features/accounts/pages/accounts/accounts.component';
import { AccountDetailsComponent } from './features/accounts/pages/accounts/account-details/account-details.component';
import { ProfileDetailsComponent } from './features/profile/pages/profile-details/profile-details.component';
import { SignupComponent } from './features/auth/pages/signup/signup.component';
import { LoginComponent } from './features/auth/pages/login/login.component';
import { SignupVerificationComponent } from './features/auth/pages/signup-verification/signup-verification.component';

export const routes: Routes = [
  {
    path: '',
    component: AccountsComponent,
    children: [
      {
        path: 'accounts/:id',
        component: AccountDetailsComponent
      }
    ]
  },
  {
    path: 'profile',
    component: ProfileDetailsComponent
  },
  {
    path: 'signup',
    component: SignupComponent
  },
  {
    path: 'activate',
    component: SignupVerificationComponent
  },
  {
    path: 'login',
    title: 'Login',
    component: LoginComponent
  }
];
