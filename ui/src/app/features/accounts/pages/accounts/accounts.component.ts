import { Component, OnInit } from '@angular/core';
import { AccountApiService } from '../../account-api.service';
import { Router, RouterOutlet } from '@angular/router';
import { LoadingState } from '../../../../types/loading-state';

@Component({
  selector: 'app-accounts',
  imports: [
    RouterOutlet
  ],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css'
})
export class AccountsComponent implements OnInit {
  loading = true;
  accounts: any[] = [];

  constructor(
    private accountApiService: AccountApiService,
    private router: Router
  ) {}

  ngOnInit() {
    this.accountApiService.getAllAccounts().subscribe((response) => {
      if (response.state === 'loaded') {
        this.loading = false;
        this.accounts = response.data;
      }
    })
  }

  accountClicked(id: string) {
    this.router.navigate(['/accounts/', id])
  }
}
