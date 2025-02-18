import {
  ApplicationConfig,
  inject,
  provideAppInitializer,
  provideZoneChangeDetection
} from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { deviceHeaderInterceptor } from './core/interceptors/device-header.interceptor';
import { ThemeService } from './core/services/theme.service';
import { AuthService } from './core/services/auth.service';
import { unauthorizedResponseInterceptor } from './core/interceptors/unauthorized-response-interceptor.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(
      withInterceptors([
        deviceHeaderInterceptor,
        unauthorizedResponseInterceptor
      ])
    ),
    provideAppInitializer(() => {
      const themeService = inject(ThemeService);
      themeService.loadTheme();
    }),
    provideAppInitializer(() => {
      const authService = inject(AuthService);
      authService.self();
    })
  ]
};
