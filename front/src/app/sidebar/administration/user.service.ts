import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Role, User } from './user.type';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private API_URL = 'http://localhost:8080/user';
  private userConnected: User | null = null;

  constructor(private http: HttpClient) {}

  isAdmin(){
    return this.getUserConnected()?.role == Role.admin
  }

  canAdd(){
    if (this.getUserConnected()?.role == Role.admin) {
      return true;
    } 
    return false
    // return this.getUserConnected()?.role == Role.admin
  }

  canEdit(){
    return this.getUserConnected()?.role == Role.admin || this.getUserConnected()?.role == Role.editor
  }

  canDelete(){
    return this.getUserConnected()?.role == Role.admin
  }

  getUserConnected(){
    return this.userConnected;
  }

  me() {
    return this.http.get<User>(`${this.API_URL}/me`).pipe(
      tap({
        next: (res) => {
          this.userConnected = res;
        },
        error: () => {
          this.userConnected = null;
        }
      })
    );
  }

  delete(trigramme: string) {
    return this.http.delete<User>(`${this.API_URL}/${trigramme}`);
  }

  findAll() {
    return this.http.get<User[]>(`${this.API_URL}`);
  }

  update(trigramme: string, user: User) {
    return this.http.put<User>(`${this.API_URL}/${trigramme}`, user);
  }
}
