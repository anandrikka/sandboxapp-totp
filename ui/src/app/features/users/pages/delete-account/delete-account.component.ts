import { Component, EventEmitter, Output } from '@angular/core';
import { SettingsSubHeaderComponent } from '../../../settings/settings-sub-header/settings-sub-header.component';
import { UserApiService } from '../../user-api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-delete-account',
  imports: [
    SettingsSubHeaderComponent
  ],
  template: `
    <div class="flex flex-col">
      <app-settings-sub-header title="Delete Account" (cancel)="cancel.emit()"></app-settings-sub-header>
      <p class="text-xl text-center px-2 py-4">
        Are you sure, want to delete the account?
      </p>
      <div class="flex flex-row items-center justify-center gap-4">
        <button class="btn btn--primary" (click)="cancel.emit()">No</button>
        <button class="btn btn--primary" (click)="deleteAccount()">Yes</button>
      </div>
    </div>
  `,
  styles: ``
})
export class DeleteAccountComponent {
  @Output() cancel = new EventEmitter()

  constructor(
    private userApiService: UserApiService,
    private router: Router
  ) {}

  deleteAccount() {
    this.userApiService.delete().subscribe(() => this.router.navigate(['/']))
  }
}
