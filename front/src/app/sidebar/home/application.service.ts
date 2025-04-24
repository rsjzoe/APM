import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {
  Application,
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

  findAll() {
    return this.http.get<Application[]>(this.apiUrl);
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

  update(id: number, application: UpdateApplication) {
    return this.http.put<Application>(`${this.apiUrl}/${id}`, application);
  }

  findById(id: number) {
    return this.http.get<Application>(`${this.apiUrl}/${id}`);
  }
}
