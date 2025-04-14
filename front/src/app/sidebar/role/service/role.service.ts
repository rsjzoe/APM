import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {
  CreateRole,
  HasAccess,
  HasAccessOutput,
  Role,
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
      .get<HasAccessOutput>(this.apiUrl + '/hasAccess?' + query.toString())
      .pipe(map((response) => response.ok));
  }

  // delete(id: number) {
  //   return this.http.delete<Classe>(`${this.apiUrl}/${id}`);
  // }

  // update(id: number, data: UpdateClasse) {
  //   return this.http.put<Classe>(`${this.apiUrl}/${id}`, data);
  // }

  // findById(id: number) {
  //   return this.http.get<Classe>(`${this.apiUrl}/${id}`);
  // }
}
