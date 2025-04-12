import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { CostMonth } from './cost.type';

@Injectable({
  providedIn: 'root',
})
export class CostService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/cost';

  findCostLatestPerMonthByAppId(appId: number) {
    return this.http.get<CostMonth[]>(
      `${this.apiUrl}/latest-per-month/${appId}`
    );
  }
}
