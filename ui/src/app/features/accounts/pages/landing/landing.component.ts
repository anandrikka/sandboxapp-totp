import { Component, OnInit } from '@angular/core';
import { AccountApiService } from '../../account-api.service';
import { AccountDetailsComponent } from './account-details/account-details.component';
import { PlusIconComponent } from '../../../../lib/components/icons/plus-icon.component';
import { Account } from '../../../../types';

@Component({
  selector: 'app-landing',
  imports: [
    PlusIconComponent,
    AccountDetailsComponent
  ],
  templateUrl: './landing.component.html'
})
export class LandingComponent implements OnInit {
  loading = true;
  accounts: any[] = [];
  activeAccount?: Account;

  constructor(
    private accountApiService: AccountApiService
  ) {}

  ngOnInit() {
    this.accountApiService.getAllAccounts().subscribe((response) => {
      if (response.status === 'loaded') {
        this.loading = false;
        this.accounts = response.data;
        this.activeAccount = this.accounts[0];
      }
    })
  }

  accountClicked(account: Account) {
    this.activeAccount = account;
  }
}
