import { inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map, Observable, of, startWith } from 'rxjs';
import { LoadingState } from '../../types';

export abstract class ApiService {
  protected httpClient = inject(HttpClient);
  protected path: string;

  protected constructor(path: string) {
    this.path = path;
  }

  buildUrl(path?: string): string {
    return `${this.path}${path ?? ''}`
  }

  requestState(source$: Observable<any>): Observable<LoadingState> {
    return source$.pipe(
      map((data) => ({ status: 'loaded' as const, data })),
      catchError((error) => of({ status: 'error' as const, error: error.error })),
      startWith({ status: 'loading' as const })
    );
  }
}
