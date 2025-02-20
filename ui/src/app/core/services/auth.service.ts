import { Injectable } from '@angular/core';
import { UserApiService } from '../../features/users/user-api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  user: any = {};
  constructor(
    private userApiService: UserApiService
  ) {}

  self() {
    this.userApiService.self().then((response) => {
      this.user = response;
    });
  }
}
