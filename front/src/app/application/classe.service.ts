import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { ClasseBackend, transformClasseBackendToClasse } from './appBackend';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ClasseService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/classes';

  findAll() {
    return this.http
      .get<ClasseBackend[]>(this.apiUrl)
      .pipe(
        map((data: ClasseBackend[]) =>
          data.map((element) => transformClasseBackendToClasse(element))
        )
      );
  }
}
