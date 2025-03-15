import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {
  CategoryODAParentBackend,
  transformCategoryODAParentBackendToCategoryODAParent,
  transformCreateCategoryODAParentToBackend,
  transformUpdateCategoryODAParentToBackend,
} from '../../../application/appBackend';
import { map, Observable } from 'rxjs';
import {
  CreateCategoryODAParent,
  UpdateCategoryODAParent,
} from '../../../application/appType';

@Injectable({
  providedIn: 'root',
})
export class CategoryODAParentService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/category-oda-parent';

  findAll() {
    return this.http
      .get<CategoryODAParentBackend[]>(this.apiUrl)
      .pipe(
        map((CategoryODAParents: CategoryODAParentBackend[]) =>
          CategoryODAParents.map((CategoryODAParent) =>
            transformCategoryODAParentBackendToCategoryODAParent(
              CategoryODAParent
            )
          )
        )
      );
  }

  add(data: CreateCategoryODAParent) {
    return this.categoryParentBackendToCategoryParent(
      this.http.post<CategoryODAParentBackend>(
        this.apiUrl,
        transformCreateCategoryODAParentToBackend(data)
      )
    );
  }

  delete(id: number) {
    return this.categoryParentBackendToCategoryParent(
      this.http.delete<CategoryODAParentBackend>(`${this.apiUrl}/${id}`)
    );
  }

  update(id: number, data: UpdateCategoryODAParent) {
    return this.categoryParentBackendToCategoryParent(
      this.http.put<CategoryODAParentBackend>(
        `${this.apiUrl}/${id}`,
        transformUpdateCategoryODAParentToBackend(data)
      )
    );
  }

  findById(id: number) {
    return this.categoryParentBackendToCategoryParent(
      this.http.get<CategoryODAParentBackend>(`${this.apiUrl}/${id}`)
    );
  }

  categoryParentBackendToCategoryParent(
    data: Observable<CategoryODAParentBackend>
  ) {
    return data.pipe(
      map((categoryParentBackend: CategoryODAParentBackend) => {
        return transformCategoryODAParentBackendToCategoryODAParent(
          categoryParentBackend
        );
      })
    );
  }
}
