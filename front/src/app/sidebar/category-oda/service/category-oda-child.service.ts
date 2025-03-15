import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import {
  CategoryODAChild,
  CreateCategoryODAChild,
  UpdateCategoryODAChild,
} from '../../../application/category.type';

@Injectable({
  providedIn: 'root',
})
export class CategoryODAChildService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/category-oda-child';

  findAll() {
    return this.http.get<CategoryODAChild[]>(this.apiUrl);
  }

  add(data: CreateCategoryODAChild) {
    return this.http.post<CategoryODAChild>(this.apiUrl, data);
  }

  delete(id: number) {
    return this.http.delete<CategoryODAChild>(`${this.apiUrl}/${id}`);
  }

  update(id: number, data: UpdateCategoryODAChild) {
    return this.http.put<CategoryODAChild>(`${this.apiUrl}/${id}`, data);
  }

  findById(id: number) {
    return this.http.get<CategoryODAChild>(`${this.apiUrl}/${id}`);
  }
}
