import { Component } from '@angular/core';
import { SettingsSubHeaderComponent } from '../../../settings/settings-sub-header/settings-sub-header.component';
import { Router } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AccountApiService } from '../../account-api.service';

@Component({
  selector: 'app-add-account',
  imports: [
    SettingsSubHeaderComponent,
    ReactiveFormsModule
  ],
  templateUrl: './add-account.component.html',
  styles: ``
})
export class AddAccountComponent {
  accountForm = new FormGroup({
    issuer: new FormControl(''),
    name: new FormControl('', { validators: [Validators.required] }),
    secret: new FormControl('', { validators: [ Validators.required ] }),
    algorithm: new FormControl('HmacSHA1')
  })

  constructor(
    private router: Router,
    private accountApiService: AccountApiService
  ) {}

  closeAddAccount() {
    this.router.navigate(['/'])
  }

  addAccount() {
    if (this.accountForm.valid) {
      this.accountApiService.create(this.accountForm.value).subscribe(() => {
        this.closeAddAccount();
      });
    }
  }
}
