import { computed, Injectable, signal } from '@angular/core';
import { UserApiService } from '../../features/users/user-api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  user = signal<Record<string, string>>({});
  loggedIn = computed(() => Object.keys(this.user()).length > 0)

  constructor(
    private userApiService: UserApiService
  ) {}

  self() {
    this.userApiService.self().then((response) => {
      this.user.set(response as Record<string, string>);
    });
  }
}
