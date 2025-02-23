import { Component, OnInit } from '@angular/core';
import { AccountApiService } from '../../account-api.service';
import { Router } from '@angular/router';
import { AccountDetailsComponent } from './account-details/account-details.component';
import { PlusIconComponent } from '../../../../lib/components/icons/plus-icon.component';
import { Account } from '../../../../types';

@Component({
  selector: 'main.app-content',
  imports: [
    PlusIconComponent,
    AccountDetailsComponent
  ],
  templateUrl: './accounts.component.html'
})
export class AccountsComponent implements OnInit {
  loading = true;
  accounts: any[] = [];
  activeAccount?: Account;

  constructor(
    private accountApiService: AccountApiService,
    private router: Router
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
