import {
  HttpErrorResponse,
  HttpInterceptorFn,
  HttpStatusCode
} from '@angular/common/http';
import { catchError, tap, throwError } from 'rxjs';
import { inject } from '@angular/core';
import { Router } from '@angular/router';

export const unauthorizedResponseInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);
  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      // Handle Unauthorized errors in the error response
      if (error.status === HttpStatusCode.Unauthorized) {
        router.navigate(['/login']);
      }
      return throwError(() => error); // Ensure the observable doesn't break
    })
  );
};
