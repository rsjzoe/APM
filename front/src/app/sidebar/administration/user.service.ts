import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ChangePassword, User, UserQuery } from './user.type';
import { firstValueFrom, map, tap } from 'rxjs';
import { RoleService } from '../role/service/role.service';
import { ActionType, ServiceName } from '../../application/role/role.type';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private API_URL = 'http://localhost:8080/user';
  private userConnected: User | null = null;

  constructor(private http: HttpClient, private roleService: RoleService) {}

  isAdmin() {
    return this.getUserConnected()?.role == 'admin';
  }

  hasAccess(serviceName: ServiceName, action: ActionType) {
    return this.roleService.hasAccess({
      action: action,
      roleName: this.getUserConnected()?.role || '',
      serviceName: serviceName,
    });
  }

  canReadService(serviceName: ServiceName) {
    return this.roleService.hasAccess({
      action: 'canRead',
      roleName: this.getUserConnected()?.role || '',
      serviceName: serviceName,
    });
  }

  canCreateService(serviceName: ServiceName) {
    return this.roleService.hasAccess({
      action: 'canCreate',
      roleName: this.getUserConnected()?.role || '',
      serviceName: serviceName,
    });
  }

  canEditService(serviceName: ServiceName) {
    return this.roleService.hasAccess({
      action: 'canUpdate',
      roleName: this.getUserConnected()?.role || '',
      serviceName: serviceName,
    });
  }

  canDeleteService(serviceName: ServiceName) {
    return this.roleService.hasAccess({
      action: 'canDelete',
      roleName: this.getUserConnected()?.role || '',
      serviceName: serviceName,
    });
  }

  canEdit() {
    return (
      this.getUserConnected()?.role == 'admin' ||
      this.getUserConnected()?.role == 'editor'
    );
  }

  canDelete() {
    return this.getUserConnected()?.role == 'admin';
  }

  getUserConnected() {
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
        },
      })
    );
  }

  delete(trigramme: string) {
    return this.http.delete<User>(`${this.API_URL}/${trigramme}`);
  }

  findAll(query: UserQuery = {}) {
    const searchParams = new URLSearchParams();
    if (query.role) {
      searchParams.set('role', query.role);
    }
    if (query.search) {
      searchParams.set('search', query.search);
    }
    return this.http.get<User[]>(`${this.API_URL}?${searchParams.toString()}`);
  }

  update(trigramme: string, user: User) {
    return this.http.put<User>(`${this.API_URL}/${trigramme}`, user);
  }

  changePassword(trigramme: string, password: ChangePassword) {
    return this.http.put<User>(
      `${this.API_URL}/change-password/${trigramme}`,
      password
    );
  }
}
