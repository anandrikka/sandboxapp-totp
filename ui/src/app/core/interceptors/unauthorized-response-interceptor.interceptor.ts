import {
  HttpErrorResponse,
  HttpInterceptorFn,
  HttpStatusCode
} from '@angular/common/http';
import { catchError, switchMap, tap, throwError } from 'rxjs';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthApiService } from '../../features/auth/auth-api.service';

export const unauthorizedResponseInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);
  const authApiService = inject(AuthApiService);

  if (req.url.includes('/api/v1/auth/refresh_token')) {
    return next(req);
  }

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === HttpStatusCode.Unauthorized) {
        return authApiService.refreshToken().pipe(
          switchMap(() => next(req)),
          catchError(() => {
            router.navigate(['/login'])
            return throwError(() => error)
          })
        )
      }
      return throwError(() => error);
    })
  );
};
