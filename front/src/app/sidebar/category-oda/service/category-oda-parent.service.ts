import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import {
  CategoryODAParent,
  CreateCategoryODAParent,
  UpdateCategoryODAParent,
} from '../../../application/category/category.type';

@Injectable({
  providedIn: 'root',
})
export class CategoryODAParentService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/category-oda-parent';

  findAll() {
    return this.http.get<CategoryODAParent[]>(this.apiUrl);
  }

  add(data: CreateCategoryODAParent) {
    return this.http.post<CategoryODAParent>(this.apiUrl, data);
  }

  delete(id: number) {
    return this.http.delete<CategoryODAParent>(`${this.apiUrl}/${id}`);
  }

  update(id: number, data: UpdateCategoryODAParent) {
    return this.http.put<CategoryODAParent>(`${this.apiUrl}/${id}`, data);
  }

  findById(id: number) {
    return this.http.get<CategoryODAParent>(`${this.apiUrl}/${id}`);
  }
}
