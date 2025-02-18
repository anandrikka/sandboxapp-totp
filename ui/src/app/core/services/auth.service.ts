import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  loggedIn = false;
  constructor(
    private httpClient: HttpClient,
    private router: Router
  ) {}

  self() {
    this.httpClient.get('/api/v1/users/self').subscribe((response) => {
      console.log(response);
    }, (error) => {
      this.router.navigate(['/login'])
    });
  }
}
