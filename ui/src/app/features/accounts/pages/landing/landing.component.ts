import { Component, OnInit } from '@angular/core';
import { AccountApiService } from '../../account-api.service';
import { AccountDetailsComponent } from './account-details/account-details.component';
import { PlusIconComponent } from '../../../../lib/components/icons/plus-icon.component';
import { Account } from '../../../../types';
import { AuthService } from '../../../../core/services/auth.service';
import { Router } from '@angular/router';

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
    private accountApiService: AccountApiService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.authService.self()
    this.accountApiService.getAllAccounts().subscribe((response) => {
      if (response.status === 'loaded') {
        this.loading = false;
        this.accounts = response.data;
        this.activeAccount = this.accounts[0];
      }
    })
  }

  addAccount() {
    this.router.navigate(['/accounts/new'])
  }

  accountClicked(account: Account) {
    this.activeAccount = account;
  }
}
