import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {
  CategoryODAChildBackend,
  transformCategoryODAChildBackendToCategoryODAChild,
  transformCreateCategoryODAChildToBackend,
  transformUpdateCategoryODAChildToBackend,
} from '../../../application/appBackend';
import { map, Observable } from 'rxjs';
import {
  CreateCategoryODAChild,
  UpdateCategoryODAChild,
} from '../../../application/appType';

@Injectable({
  providedIn: 'root',
})
export class CategoryODAChildService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/category-oda-child';

  findAll() {
    return this.http
      .get<CategoryODAChildBackend[]>(this.apiUrl)
      .pipe(
        map((CategoryODAChilds: CategoryODAChildBackend[]) =>
          CategoryODAChilds.map((CategoryODAChild) =>
            transformCategoryODAChildBackendToCategoryODAChild(
              CategoryODAChild
            )
          )
        )
      );
  }

  add(data: CreateCategoryODAChild) {
    return this.categoryChildBackendToCategoryChild(
      this.http.post<CategoryODAChildBackend>(
        this.apiUrl,
        transformCreateCategoryODAChildToBackend(data)
      )
    );
  }

  delete(id: number) {
    return this.categoryChildBackendToCategoryChild(
      this.http.delete<CategoryODAChildBackend>(`${this.apiUrl}/${id}`)
    );
  }

  update(id: number, data: UpdateCategoryODAChild) {
    return this.categoryChildBackendToCategoryChild(
      this.http.put<CategoryODAChildBackend>(
        `${this.apiUrl}/${id}`,
        transformUpdateCategoryODAChildToBackend(data)
      )
    );
  }

  findById(id: number) {
    return this.categoryChildBackendToCategoryChild(
      this.http.get<CategoryODAChildBackend>(`${this.apiUrl}/${id}`)
    );
  }

  categoryChildBackendToCategoryChild(
    data: Observable<CategoryODAChildBackend>
  ) {
    return data.pipe(
      map((categoryChildBackend: CategoryODAChildBackend) => {
        return transformCategoryODAChildBackendToCategoryODAChild(
          categoryChildBackend
        );
      })
    );
  }
}
