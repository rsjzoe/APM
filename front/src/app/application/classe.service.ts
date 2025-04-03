import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Classe, CreateClasse, UpdateClasse } from './classe.type';

@Injectable({
  providedIn: 'root',
})
export class ClasseService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/classe';

  findAll() {
    return this.http.get<Classe[]>(this.apiUrl);
  }

  add(data: CreateClasse) {
    return this.http.post<Classe>(this.apiUrl, data);
  }

  delete(id: number) {
    return this.http.delete<Classe>(`${this.apiUrl}/${id}`);
  }

  update(id: number, data: UpdateClasse) {
    return this.http.put<Classe>(`${this.apiUrl}/${id}`, data);
  }

  findById(id: number) {
    return this.http.get<Classe>(`${this.apiUrl}/${id}`);
  }
}
