import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {
  DepartementBackend,
  departementBackendToDepartement,
} from './appBackend';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DepartementService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/departements';

  findAll() {
    return this.http
      .get<DepartementBackend[]>(this.apiUrl + "/list")
      .pipe(
        map((departementBackend: DepartementBackend[]) =>
          departementBackend.map((element) =>
            departementBackendToDepartement(element)
          )
        )
      );
  }
}
