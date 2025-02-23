import { Routes } from '@angular/router';
import { LandingComponent } from './features/accounts/pages/landing/landing.component';
import { AccountDetailsComponent } from './features/accounts/pages/landing/account-details/account-details.component';
import { SignupComponent } from './features/auth/pages/signup/signup.component';
import { LoginComponent } from './features/auth/pages/login/login.component';
import { SignupVerificationComponent } from './features/auth/pages/signup-verification/signup-verification.component';
import { DevicesComponent } from './features/devices/pages/devices/devices.component';
import { ProfileDetailsComponent } from './features/users/pages/profile-details/profile-details.component';
import { SettingsComponent } from './features/settings/settings.component';
import { AccountsComponent } from './features/accounts/pages/accounts/accounts.component';

export const routes: Routes = [
  {
    path: '',
    component: LandingComponent
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
        component: ProfileDetailsComponent
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
