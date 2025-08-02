import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {
  Application,
  ApplicationQuery,
  CreateApplication,
  UpdateApplication,
} from '../../application/app.type';
import { UserService } from '../administration/user.service';

@Injectable({
  providedIn: 'root',
})
export class ApplicationService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/application';

  constructor(private userService: UserService) {}

  findAll(query: ApplicationQuery = {}) {
    const searchParams = new URLSearchParams();
    if (query.year) {
      searchParams.set('year', query.year.toString());
    }
    if (query.search) {
      searchParams.set('search', query.search);
    }
    if (query.departementId) {
      searchParams.set('departementId', query.departementId.toString());
    }
    return this.http.get<Application[]>(
      `${this.apiUrl}?${searchParams.toString()}`
    );
  }

  add(application: CreateApplication) {
    const { documentations, ...newApplication } = application;
    const formData = new FormData();

    formData.append(
      'newApplication',
      new Blob([JSON.stringify(newApplication)], { type: 'application/json' })
    );

    documentations.forEach((doc, index) => {
      formData.append('files', doc.file);
      formData.append('types', doc.type);
    });

    return this.http.post<Application>(this.apiUrl, formData);
  }

  delete(id: number) {
    return this.http.delete<Application>(`${this.apiUrl}/${id}`);
  }

  restore(id: number) {
    return this.http.put<Application>(`${this.apiUrl}/restore/${id}`, {});
  }

  findAllDeleted() {
    return this.http.get<Application[]>(`${this.apiUrl}/deleted/list`);
  }

  update(id: number, application: UpdateApplication) {
    return this.http.put<Application>(`${this.apiUrl}/${id}`, application);
  }

  findById(id: number) {
    return this.http.get<Application>(`${this.apiUrl}/${id}`);
  }
}
