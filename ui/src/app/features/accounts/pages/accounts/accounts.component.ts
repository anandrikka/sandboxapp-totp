import { Component, OnInit } from '@angular/core';
import { AccountApiService } from '../../account-api.service';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-accounts',
  imports: [
    RouterOutlet
  ],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css'
})
export class AccountsComponent implements OnInit {

  constructor(private accountApiService: AccountApiService) {}

  ngOnInit() {
    this.accountApiService.getAllAccounts().subscribe()
  }
}
