import { Component, EventEmitter, Output, viewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../../core/services/auth.service';
import { SettingsSubHeaderComponent } from '../../../settings/settings-sub-header/settings-sub-header.component';

@Component({
  selector: 'app-change-email',
  imports: [
    FormsModule,
    SettingsSubHeaderComponent
  ],
  template: `
    <div class="flex flex-col">
      <app-settings-sub-header title="Change Email" (cancel)="cancel.emit()"></app-settings-sub-header>
      <div class="input-group mb-4 mt-10">
        <label class="input-group__label">Email</label>
        <div class="input-group__input">{{ authService.user()['email'] }}</div>
      </div>
      <div class="input-group">
        <label class="input-group__label">New Email</label>
        <input class="input-group__input" [(ngModel)]="newEmail">
      </div>
      <button class="bg-accent text-foreground px-4 py-2 rounded-sm">Change Email</button>
    </div>
  `,
  styles: ``
})
export class ChangeEmailComponent {
  newEmail = '';
  @Output() cancel = new EventEmitter();

  constructor(protected authService: AuthService) {
  }
}
