import { Component, OnInit } from '@angular/core';
import { AccountApiService } from '../../../account-api.service';
import { ActivatedRoute } from '@angular/router';
import { interval, switchMap, tap } from 'rxjs';

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
export class AccountDetailsComponent implements OnInit {
  passcodes: OTP[] = [];
  period: number = 30;
  countdown: number;
  activePasscode?: string
  loaded: boolean = false;

  constructor(
    private accountApiService: AccountApiService,
    private route: ActivatedRoute
  ) {
    this.countdown = this.getCountdownValue();
  }

  ngOnInit() {
    this.route.params.pipe(
      tap((params) => this.resetState()),
      switchMap((params) => this.fetchPasscodes(params['id'])),
      tap(() => {
        this.loaded = true;
      }),
      switchMap(() => interval(1000).pipe(
        tap(() => {
          this.countdown--;
          if (this.countdown === 0) {
            this.passcodes = [...this.passcodes.slice(1)]
            this.setActivePasscode();
            this.countdown = this.getCountdownValue();
            this.passcodes.length === 1 && this.fetchPasscodes(this.route.snapshot.params['id']).subscribe()
          }
        })
      ))
    ).subscribe()
  }

  resetState() {
    this.loaded = false;
    this.passcodes = [];
    this.period = 30;
    this.activePasscode = undefined;
  }

  fetchPasscodes(accountId: string) {
    return this.accountApiService.getOtps(accountId).pipe(
      tap((response: any) => {
        this.period = response.period;
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
    this.activePasscode = this.passcodes.find((passcode) => passcode.counter === counter)?.code;
  }

  getCountdownValue() {
    return Math.floor(this.period - (Math.floor(Date.now() / 1000) % this.period));
  }

  protected readonly JSON = JSON;
}
