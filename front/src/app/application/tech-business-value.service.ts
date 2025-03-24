import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { TechBusinessValueMonth } from './techBusinessValue.type';

@Injectable({
  providedIn: 'root',
})
export class TechBusinessValueService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/techBusinessvalue';

  findTechBusinessValueLatestPerMonthByAppId(appId: number) {
    return this.http.get<TechBusinessValueMonth[]>(
      `${this.apiUrl}/latest-per-month/${appId}`
    );
  }
}
