import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {
  BudgetHistoryBackend,
  transformBudgetHistoryBackendToBudget,
  transformCreateBudgetToCreateBudgetBackend,
  transformUpdateBudgetToUpdateBudgetBackend,
} from './appBackend';
import { map } from 'rxjs';
import { CreateBudget, UpdateBudget } from './appType';

@Injectable({
  providedIn: 'root',
})
export class BudgetService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/budget';

  findAllByAppId(appID: number) {
    return this.http
      .get<BudgetHistoryBackend[]>(`${this.apiUrl}/history/${appID}`)
      .pipe(
        map((appHistoryBackend) =>
          appHistoryBackend.map((element) =>
            transformBudgetHistoryBackendToBudget(element)
          )
        )
      );
  }

  addBugetIntoApp(appId: number, data: CreateBudget) {
    return this.http.post<void>(
      this.apiUrl + '/insert/' + appId,
      transformCreateBudgetToCreateBudgetBackend(data)
    );
  }

  updateBugetById(id: number, data: UpdateBudget) {
    return this.http.patch<void>(
      this.apiUrl + '/update/' + id,
      transformUpdateBudgetToUpdateBudgetBackend(data)
    );
  }
}
