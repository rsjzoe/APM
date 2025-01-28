import { inject, Injectable } from '@angular/core';
import {
  CreateQuestion,
  Question,
  UpdateQuestion,
} from '../../application-APM/appType';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class QuestionService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/question';

  constructor() {}

  findAll() {
    return this.http.get<Question[]>(this.apiUrl);
  }

  add(question: CreateQuestion) {
    return this.http.post<Question>(this.apiUrl, question);
  }

  delete(id: number) {
    return this.http.delete<Question>(`${this.apiUrl}/${id}`);
  }

  update(id: number, question: UpdateQuestion) {
    return this.http.put<Question>(`${this.apiUrl}/${id}`, question);
  }

  findById(id: number) {
    return this.http.get<Question>(`${this.apiUrl}/${id}`);
  }
}
