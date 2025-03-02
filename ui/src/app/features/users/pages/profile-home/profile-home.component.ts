import { Component } from '@angular/core';
import { ProfileDetailsComponent } from '../profile-details/profile-details.component';
import { ChangeEmailComponent } from '../change-email/change-email.component';
import { DeleteAccountComponent } from '../delete-account/delete-account.component';

@Component({
  selector: 'app-profile-home',
  imports: [
    ProfileDetailsComponent,
    ChangeEmailComponent,
    DeleteAccountComponent
  ],
  template: `
    @if(state === 'change-email') {
      <app-change-email (cancel)="this.state = 'default'"></app-change-email>
    } @else if (state === 'delete-account') {
      <app-delete-account (cancel)="this.state = 'default'"></app-delete-account>
    } @else {
      <app-profile-details (changeState)="changeState($event)"></app-profile-details>
    }
  `,
  styles: ``
})
export class ProfileHomeComponent {
  state: string = 'default'

  changeState(newState: string) {
    this.state = newState;
  }
}
