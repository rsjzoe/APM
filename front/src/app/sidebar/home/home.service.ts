import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {
  Application,
  CreateApplication,
  UpdateApplication,
} from '../../application-APM/appType';

@Injectable({
  providedIn: 'root',
})
export class ApplicationService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/application';

  constructor() {}

  findAll() {
    return this.http.get<Application[]>(this.apiUrl);
  }

  add(application: CreateApplication) {
    return this.http.post<Application>(this.apiUrl, application);
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
