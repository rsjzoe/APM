import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { of, tap } from 'rxjs';
import { Register, Token } from './authType';
import { User } from '../sidebar/administration/user.type';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private API_URL = 'http://localhost:8080/auth';

  constructor(private http: HttpClient, private router: Router) {}

  login(trigramme: string, password: string) {
    return this.http
      .post<Token>(`${this.API_URL}/login`, { trigramme, password })
      .pipe(
        tap((res) => {
          this.storeTokens(res.accessToken, res.refreshToken);
        })
      );
  }

  getToken(): string | null {
    return localStorage.getItem('access_token');
  }

  storeTokens(accessToken: string, refreshToken: string) {
    localStorage.setItem('access_token', accessToken);
    localStorage.setItem('refresh_token', refreshToken);
  }

  getRefreshtoken(): string | null {
    return localStorage.getItem('refresh_token');
  }

  refreshToken() {
    const refreshToken = this.getRefreshtoken();
    return this.http
      .get<Token>(`${this.API_URL}/refreshToken?refreshToken=${refreshToken}`)
      .pipe(
        tap((response) => {
          this.storeTokens(response.accessToken, response.refreshToken);
        })
      );
  }

  logout() {
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    this.router.navigate(['/login']);
  }

  register(register: Register) {
    return this.http.post<User>(`${this.API_URL}/register`, register);
  }
}
