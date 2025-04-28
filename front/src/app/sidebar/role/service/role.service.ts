import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {
  CreateRole,
  HasAccess,
  HasAccessOutput,
  Role,
  UpdateRole,
} from '../../../application/role/role.type';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RoleService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/role';

  findAll() {
    return this.http.get<Role[]>(this.apiUrl);
  }

  add(data: CreateRole) {
    return this.http.post<Role>(this.apiUrl, data);
  }

  hasAccess(data: HasAccess) {
    const query = new URLSearchParams(data);
    return this.http
      .get<HasAccessOutput>(this.apiUrl + '/has-access?' + query.toString())
      .pipe(map((response) => response.ok));
  }

  deleteByName(name: string) {
    return this.http.delete<Role>(`${this.apiUrl}/${name}`);
  }

  update(id: number, data: UpdateRole) {
    return this.http.put<Role>(`${this.apiUrl}/${id}`, data);
  }

  findByName(name: string) {
    return this.http.get<Role>(`${this.apiUrl}/${name}`);
  }
}
