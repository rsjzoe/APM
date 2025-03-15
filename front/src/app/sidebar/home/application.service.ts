import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {
  Application,
  CreateApplication,
  UpdateApplication,
} from '../../application/appType';
import {
  AppBackend,
  AppDetailsBackend,
  transformAppBackendToApplication,
  transformAppDetailsBackendToApplication,
  transformCreateApplicationToCreateAppBackend,
  transformUpdateApplicationToBackend,
} from '../../application/appBackend';
import { map, Observable } from 'rxjs';
import { UserService } from '../administration/user.service';
import { Role } from '../administration/user.type';

@Injectable({
  providedIn: 'root',
})
export class ApplicationService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/application';

  constructor(private userService: UserService) {}

  findAll() {
    return this.http
      .get<AppBackend[]>(this.apiUrl + '/list/active')
      .pipe(
        map((appBackends: AppBackend[]) =>
          appBackends.map((appBackend) =>
            transformAppBackendToApplication(appBackend)
          )
        )
      );
  }

  add(application: CreateApplication) {
    if (this.userService.getUserConnected()?.role != Role.admin) {
      throw new Error('Only admins can add applications');
    }
    const data = transformCreateApplicationToCreateAppBackend(application);
    const formData = new FormData();

    formData.append(
      'dto',
      new Blob([JSON.stringify(data)], { type: 'application/json' })
    );

    application.documentations.forEach((doc, index) => {
      formData.append('files', doc.file);
      formData.append('types', doc.type);
    });

    return this.appBackendToApp(
      this.http.post<AppBackend>(this.apiUrl + '/insert', formData)
    );
  }

  delete(id: number) {
    return this.appBackendToApp(
      this.http.delete<AppBackend>(`${this.apiUrl}/delete/${id}`)
    );
  }

  update(id: number, application: UpdateApplication) {
    return this.appBackendToApp(
      this.http.patch<AppBackend>(
        `${this.apiUrl}/update/${id}`,
        transformUpdateApplicationToBackend(application)
      )
    );
  }

  findById(id: number) {
    return this.http
      .get<AppDetailsBackend>(`${this.apiUrl}/details/${id}`)
      .pipe(
        map((appBackend) => {
          return transformAppDetailsBackendToApplication(appBackend);
        })
      );
  }

  appBackendToApp(data: Observable<AppBackend>) {
    return data.pipe(
      map((appBackend: AppBackend) => {
        return transformAppBackendToApplication(appBackend);
      })
    );
  }
}
