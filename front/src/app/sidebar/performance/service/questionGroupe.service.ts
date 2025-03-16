import { inject, Injectable } from '@angular/core';
import {
  CreateQuestionGroupe,
  QuestionGroupe,
  UpdateQuestionGroupe,
} from '../../../application/question.type';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class QuestionGroupeService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/question-group';

  constructor() {}

  findAll() {
    return this.http.get<QuestionGroupe[]>(this.apiUrl);
  }

  add(question: CreateQuestionGroupe) {
    return this.http.post<QuestionGroupe>(this.apiUrl, question);
  }

  delete(id: number) {
    return this.http.delete<QuestionGroupe>(`${this.apiUrl}/${id}`);
  }

  update(id: number, question: UpdateQuestionGroupe) {
    return this.http.put<QuestionGroupe>(`${this.apiUrl}/${id}`, question);
  }

  findById(id: number) {
    return this.http.get<QuestionGroupe>(`${this.apiUrl}/${id}`);
  }
}
