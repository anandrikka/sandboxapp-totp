import { HttpInterceptorFn } from '@angular/common/http';

export const deviceHeaderInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req.clone({
    headers: req.headers.set('X-VISITOR-ID', window.fingerprintVistorId)
  }));
};
