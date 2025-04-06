import { Routes } from '@angular/router';
import { LandingComponent } from './features/accounts/pages/landing/landing.component';
import { SignupComponent } from './features/auth/pages/signup/signup.component';
import { LoginComponent } from './features/auth/pages/login/login.component';
import { SignupVerificationComponent } from './features/auth/pages/signup-verification/signup-verification.component';
import { DevicesComponent } from './features/devices/pages/devices/devices.component';
import { SettingsComponent } from './features/settings/settings.component';
import { AccountsComponent } from './features/accounts/pages/accounts/accounts.component';
import { ProfileHomeComponent } from './features/users/pages/profile-home/profile-home.component';
import { AddAccountComponent } from './features/accounts/pages/add-account/add-account.component';

export const routes: Routes = [
  {
    path: '',
    component: LandingComponent
  },
  {
    path: 'accounts/new',
    component: AddAccountComponent
  },
  {
    path: 'settings',
    component: SettingsComponent,
    children: [
      {
        path: '',
        redirectTo: '/settings/profile',
        pathMatch: 'full'
      },
      {
        path: 'profile',
        component: ProfileHomeComponent
      },
      {
        path: 'devices',
        component: DevicesComponent
      },
      {
        path: 'accounts',
        component: AccountsComponent
      }
    ]
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
