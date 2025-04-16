import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user.type';
import { firstValueFrom, map, tap } from 'rxjs';
import { RoleService } from '../role/service/role.service';
import { ActionType } from '../../application/role/role.type';

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

  hasAccess(serviceName: string, action: ActionType) {
    return this.roleService.hasAccess({
      action: action,
      roleName: this.getUserConnected()?.role || '',
      serviceName: serviceName,
    });
  }

  canReadService(serviceName: string) {
    return this.roleService.hasAccess({
      action: 'canRead',
      roleName: this.getUserConnected()?.role || '',
      serviceName: serviceName,
    });
  }

  canCreateService(serviceName: string) {
    return this.roleService.hasAccess({
      action: 'canCreate',
      roleName: this.getUserConnected()?.role || '',
      serviceName: serviceName,
    });
  }

  canEditService(serviceName: string) {
    return this.roleService.hasAccess({
      action: 'canUpdate',
      roleName: this.getUserConnected()?.role || '',
      serviceName: serviceName,
    });
  }

  canDeleteService(serviceName: string) {
    return this.roleService.hasAccess({
      action: 'canDelete',
      roleName: this.getUserConnected()?.role || '',
      serviceName: serviceName,
    });
  }

  canAddDoc(serviceName: string) {
    return this.roleService.hasAccess({
      action: 'canCreate',
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

  canDeleteDoc(serviceName: string) {
    return this.roleService.hasAccess({
      action: 'canDelete',
      roleName: this.getUserConnected()?.role || '',
      serviceName: serviceName,
    });
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

  findAll() {
    return this.http.get<User[]>(`${this.API_URL}`);
  }

  update(trigramme: string, user: User) {
    return this.http.put<User>(`${this.API_URL}/${trigramme}`, user);
  }
}
