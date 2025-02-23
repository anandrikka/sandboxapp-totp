import { Component, Input, OnInit } from '@angular/core';
import { AccountApiService } from '../../../account-api.service';
import { ActivatedRoute } from '@angular/router';
import { distinctUntilChanged, interval, of, Subject, switchMap, tap } from 'rxjs';
import { Account } from '../../../../../types';

interface OTP {
  counter: number
  code: string
}

@Component({
  selector: 'app-account-details',
  imports: [],
  templateUrl: './account-details.component.html',
  styleUrl: './account-details.component.css'
})
export class AccountDetailsComponent {
  protected _account!: Account;
  account$ = new Subject<Account>();
  passcodes: OTP[] = [];
  period: number = 30;
  countdown: number;
  activePasscode?: OTP
  loaded: boolean = false;

  @Input() set account(value: Account) {
    this._account = value;
    this.account$.next(value);
  }

  constructor(
    private accountApiService: AccountApiService
  ) {
    this.countdown = this.getCountdownValue();
    this.account$.pipe(
      tap((account) => this.resetState(account)),
      switchMap((account) => this.fetchPasscodes(account.id)),
      tap(() => {
        this.loaded = true
      }),
      switchMap(() => interval(1000).pipe(
        tap(() => {
          this.countdown--;
          if (this.countdown === 0) {
            this.passcodes = this.passcodes.filter((passcode) => passcode.counter > this.activePasscode!.counter)
            this.setActivePasscode();
            this.countdown = this.getCountdownValue();
            this.passcodes.length === 1 && this.fetchPasscodes(this._account.id).subscribe()
          }
        })
      ))
    ).subscribe();
  }

  resetState(account: Account) {
    this.loaded = false;
    this.passcodes = [];
    this.period = account.period;
    this.activePasscode = undefined;
  }

  fetchPasscodes(accountId: string) {
    return this.accountApiService.getOtps(accountId).pipe(
      tap((response: any) => {
        const newPasscodes = [ ...this.passcodes ];
        response.passcodes.forEach((passcode: any) => {
          if (!newPasscodes.find((c) => passcode.code === c.code)) {
            newPasscodes.push(passcode);
          }
        })
        this.passcodes = newPasscodes;
        this.setActivePasscode();
        this.countdown = this.getCountdownValue();
      })
    )
  }

  setActivePasscode() {
    const counter = Math.floor(Date.now() / 1000 / this.period);
    this.activePasscode = this.passcodes.find((passcode) => passcode.counter === counter);
  }

  getCountdownValue() {
    return Math.floor(this.period - (Math.floor(Date.now() / 1000) % this.period));
  }

  protected readonly JSON = JSON;
}
