import { Component, Input } from '@angular/core';
import { DocumentationService } from '../documentation.service';
import {
  CreateDocumentation,
  DocumentationType,
} from '../../../../../application/appType';

@Component({
  selector: 'app-modal-add-documentation',
  imports: [],
  templateUrl: './modal-add-documentation.component.html',
  styleUrl: './modal-add-documentation.component.scss',
})
export class ModalAddDocumentationComponent {
  @Input()
  applicationId: number | null = null;
  fonctionnelles: File[] = [];
  techniques: File[] = [];
  exploitation: File[] = [];
  @Input()
  refresh = () => {};

  constructor(private documentationService: DocumentationService) {}

  resetData() {
    this.fonctionnelles = [];
    this.techniques = [];
    this.exploitation = [];
  }

  onChangeFonctionnelle(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement?.files && inputElement.files.length > 0) {
      this.fonctionnelles = Array.from(inputElement.files);
    }
  }

  onChangeTechnique(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement?.files && inputElement.files.length > 0) {
      this.techniques = Array.from(inputElement.files);
    }
  }

  onChangeExploitation(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement?.files && inputElement.files.length > 0) {
      this.exploitation = Array.from(inputElement.files);
    }
  }

  submit() {
    if (this.applicationId == null) return;
    const allFiles: CreateDocumentation[] = [
      ...this.fonctionnelles.map((file) => ({
        type: DocumentationType.fonctionnelle,
        file,
      })),
      ...this.techniques.map((file) => ({
        type: DocumentationType.technique,
        file,
      })),
      ...this.exploitation.map((file) => ({
        type: DocumentationType.exploitation,
        file,
      })),
    ];

    allFiles.forEach((doc) => {
      this.documentationService.add(doc, this.applicationId!).subscribe({
        next: () => {
          this.resetData();
          this.refresh();
        },
        error: (err) => {
          console.error('Error adding documentation', err);
        },
      });
    });
  }
}
