import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Departement } from './appType';

@Injectable({
  providedIn: 'root',
})
export class DepartementService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/departements';

  findAll() {
    return this.http.get<Departement[]>(this.apiUrl);
  }
}
