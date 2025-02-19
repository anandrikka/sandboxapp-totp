import { inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map, Observable, of, startWith } from 'rxjs';
import { LoadingState } from '../../types/loading-state';

export abstract class ApiService {
  protected httpClient = inject(HttpClient);
  protected path: string;

  protected constructor(path: string) {
    this.path = path;
  }

  buildUrl(path?: string): string {
    return `${this.path}${path ?? ''}`
  }

  requestState<T>(source$: Observable<any>): Observable<LoadingState<T>> {
    return source$.pipe(
      map((data) => ({ state: 'loaded' as const, data })),
      catchError((error) => of({ state: 'error' as const, error })),
      startWith({ state: 'loading' as const })
    );
  }
}
