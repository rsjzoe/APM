import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { catchError, Observable, switchMap, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { Token } from './authType';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  // aperahana amin ilay requete fona le token
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let token = this.authService.getToken();

    if (
      token &&
      !req.url.includes('login') &&
      !req.url.includes('refreshToken')
    ) {
      // ra vao passena any amin back n token de verifien fona na de fa misy peermitall ary
      req = req.clone({
        setHeaders: { Authorization: `Bearer ${token}` },
      });
    }

    return next.handle(req).pipe(
      catchError((error) => {
        if (error.status === 401 && this.authService.getRefreshtoken()) {
          return this.authService.refreshToken().pipe(
            switchMap((newToken) => {
              if (newToken) {
                this.authService.storeTokens(
                  newToken.accessToken,
                  newToken.refreshToken
                );
                // averina alefa ilay rrerquete tsy nety (token expirer)
                req = req.clone({
                  setHeaders: {
                    Authorization: `Bearer ${newToken.accessToken}`,
                  },
                });
                return next.handle(req);
              } else {
                this.authService.logout();
                return throwError(() => error);
              }
            }),
            catchError(() => {
              this.authService.logout();
              return throwError(() => error);
            })
          );
        }
        return throwError(() => error);
      })
    );
  }
}
