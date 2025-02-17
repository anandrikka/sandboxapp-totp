import { inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export abstract class ApiService {
  protected httpClient = inject(HttpClient);
  protected path: string;

  protected constructor(path: string) {
    this.path = path;
  }

  buildUrl(path?: string): string {
    return `${this.path}${path ?? ''}`
  }
}
