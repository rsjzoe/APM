import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Classe } from './classe.type';

@Injectable({
  providedIn: 'root',
})
export class ClasseService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/classe';

  findAll() {
    return this.http.get<Classe[]>(this.apiUrl);
  }
}
