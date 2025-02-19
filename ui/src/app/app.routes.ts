import { Routes } from '@angular/router';
import { AccountsComponent } from './features/accounts/pages/accounts/accounts.component';
import { AccountDetailsComponent } from './features/accounts/pages/accounts/account-details/account-details.component';
import { SignupComponent } from './features/auth/pages/signup/signup.component';
import { LoginComponent } from './features/auth/pages/login/login.component';
import { SignupVerificationComponent } from './features/auth/pages/signup-verification/signup-verification.component';
import { BaseComponent } from './layouts/base/base.component';
import { DevicesComponent } from './features/devices/pages/devices/devices.component';
import { ProfileDetailsComponent } from './features/users/pages/profile-details/profile-details.component';

export const routes: Routes = [
  {
    path: '',
    component: BaseComponent,
    children: [
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
        path: 'devices',
        component: DevicesComponent
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
