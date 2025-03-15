import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {
  DocumentationBackend,
  transformCreateDocumentationToCreateDocumentationBackend,
  transformDocumentationBackendToDocumentation,
} from '../../../../application/appBackend';
import { map, Observable } from 'rxjs';
import {
  CreateDocumentation,
  Documentation,
} from '../../../../application/appType';

@Injectable({
  providedIn: 'root',
})
export class DocumentationService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/documentation';

  findAllByAppId(appId: number) {
    return this.http
      .get<DocumentationBackend[]>(this.apiUrl + '/application/' + appId)
      .pipe(
        map((data: DocumentationBackend[]) =>
          data.map((CategoryODAChild) =>
            transformDocumentationBackendToDocumentation(CategoryODAChild)
          )
        )
      );
  }

  add(data: CreateDocumentation, appId: number) {
    const create =
      transformCreateDocumentationToCreateDocumentationBackend(data);
    const formData = new FormData();
    formData.append('image', create.file);
    formData.append('type', create.type);
    formData.append('applicationId', appId.toString());
    return this.documentationBackendToDocumentation(
      this.http.post<DocumentationBackend>(this.apiUrl, formData)
    );
  }

  deleteByFileName(name: string) {
    return this.documentationBackendToDocumentation(
      this.http.delete<DocumentationBackend>(
        `${this.apiUrl}/${encodeURIComponent(name)}`
      )
    );
  }

  documentationBackendToDocumentation(data: Observable<DocumentationBackend>) {
    return data.pipe(
      map((data: DocumentationBackend) => {
        return transformDocumentationBackendToDocumentation(data);
      })
    );
  }

  downloadFile(fileName: string, name = fileName) {
    return this.http
      .get(`${this.apiUrl}/${fileName}`, { responseType: 'blob' })
      .subscribe({
        next: (blob) => {
          const downloadLink = document.createElement('a');
          const objectUrl = URL.createObjectURL(blob);

          downloadLink.href = objectUrl;
          downloadLink.download = name;
          downloadLink.click();

          URL.revokeObjectURL(objectUrl);
        },
        error: (err) => {
          console.error('Erreur lors du téléchargement du fichier', err);
        },
      });
  }

  viewFileInNewWindow(fileName: string) {
    return this.http
      .get(`${this.apiUrl}/${fileName}`, { responseType: 'blob' })
      .subscribe({
        next: (blob) => {
          const fileURL = URL.createObjectURL(blob);
          const mimeType = blob.type; // Récupérer le type du fichier

          if (mimeType === 'application/pdf' || mimeType.startsWith('image/')) {
            window.open(fileURL, '_blank');
          } else if (
            mimeType ===
            'application/vnd.openxmlformats-officedocument.wordprocessingml.document'
          ) {
            const googleViewerUrl = `https://docs.google.com/gview?url=${encodeURIComponent(
              fileURL
            )}&embedded=true`;
            window.open(googleViewerUrl, '_blank');
          } else {
            const downloadLink = document.createElement('a');
            downloadLink.href = fileURL;
            downloadLink.download = fileName;
            downloadLink.click();
          }
        },
        error: (err) => {
          console.error("Erreur lors de l'ouverture du fichier", err);
        },
      });
  }
}
