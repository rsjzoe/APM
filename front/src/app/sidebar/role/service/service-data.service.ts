import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Service } from '../../../application/role/role.type';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ServiceDataService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/services';

  findAll() {
    return new Observable<Service[]>((observer) => {
      const services: Service[] = [
        { id: 1, serviceName: 'Utilisateurs' },
        { id: 2, serviceName: 'Rôles' },
        { id: 3, serviceName: 'Rapports' },
      ];
      observer.next(services);
      observer.complete();
    });
    // return this.http.get<Service[]>(this.apiUrl);
  }
}
