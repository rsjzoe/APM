import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { AppHistoryBackend, transformAppHistoryBackendToAppHistory } from './appBackend';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppHistoryService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/application/history';

  findAllByAppId(appID : number) {
      return this.http
        .get<AppHistoryBackend[]>(`${this.apiUrl}/${appID}` )
        .pipe(
          map((appHistoryBackend: AppHistoryBackend[]) =>
            appHistoryBackend.map((element) =>
              transformAppHistoryBackendToAppHistory(element)
            )
          )
        );
    }
}
