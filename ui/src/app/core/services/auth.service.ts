import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserApiService } from '../../features/users/user-api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  user: any = {};
  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private userApiService: UserApiService
  ) {}

  self() {
    this.userApiService.self().then((response) => {
      this.user = response;
    });
  }
}
