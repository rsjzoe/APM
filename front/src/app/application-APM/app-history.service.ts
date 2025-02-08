import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { AppHistory } from './appType';

@Injectable({
  providedIn: 'root',
})
export class AppHistoryService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/application-history';

  findAllByAppId(appID: number) {
    return this.http.get<AppHistory[]>(`${this.apiUrl}/${appID}`);
  }
}
