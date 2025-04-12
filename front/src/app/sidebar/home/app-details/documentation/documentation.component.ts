import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IconViewComponent } from '../../../../components/icons/icon-view/icon-view.component';
import { IconDocumentComponent } from '../../../../components/icons/icon-document/icon-document.component';
import {
  DocumentationType,
  Documentation,
} from '../../../../application/documentation/documentation.type';
import { DocumentationService } from './documentation.service';
import { ModalAddDocumentationComponent } from './modal-add-documentation/modal-add-documentation.component';
import { IconDeleteComponent } from '../../../../components/icons/icon-delete/icon-delete.component';
import { ModalConfirmComponent } from '../../../../components/modal-confirm/modal-confirm.component';
import { UserService } from '../../../administration/user.service';
import { Role } from '../../../administration/user.type';
import { IconTechniqueComponent } from '../../../../components/icons/icon-technique/icon-technique.component';
import { IconExploitationComponent } from '../../../../components/icons/icon-exploitation/icon-exploitation.component';
import { IconFonctionnelleComponent } from '../../../../components/icons/icon-fonctionnelle/icon-fonctionnelle.component';

@Component({
  selector: 'app-documentation',
  imports: [
    CommonModule,
    IconViewComponent,
    IconDocumentComponent,
    ModalAddDocumentationComponent,
    IconDeleteComponent,
    ModalConfirmComponent,
    IconTechniqueComponent,
    IconExploitationComponent,
    IconFonctionnelleComponent,
  ],
  templateUrl: './documentation.component.html',
  styleUrl: './documentation.component.scss',
  standalone: true,
})
export class DocumentationComponent {
  @Input() applicationId!: number;
  documents: Documentation[] = [];
  groupedDocuments: Record<DocumentationType, Documentation[]> | null = null;
  appFilenameDelete: string | null = null;

  constructor(
    private docService: DocumentationService,
    public userService: UserService
  ) {}

  canDeleteDoc() {
    return (
      this.userService.getUserConnected()?.role == Role.admin ||
      this.userService.getUserConnected()?.role == Role.editor
    );
  }

  saveIdAppDelete = (filename: string) => {
    this.appFilenameDelete = filename;
  };

  onConfirmDelete = () => {
    if (this.appFilenameDelete) {
      this.deleteByFileName(this.appFilenameDelete);
    }
  };

  findAllDocByAppId() {
    this.docService.findAllByAppId(this.applicationId).subscribe((docs) => {
      this.documents = docs;
      this.groupedDocuments = this.getGroupedDocuments();
    });
  }

  deleteByFileName(name: string) {
    this.docService.deleteByFileName(name).subscribe({
      next: (value) => {
        this.refresh();
      },
    });
  }

  refresh = () => {
    this.findAllDocByAppId();
  };

  getGroupedDocuments(): Record<DocumentationType, Documentation[]> {
    return this.documents.reduce((acc, doc) => {
      if (!acc[doc.type]) {
        acc[doc.type] = [];
      }
      acc[doc.type].push(doc);
      return acc;
    }, {} as Record<DocumentationType, Documentation[]>);
  }

  getDocumentTypeClass(type: DocumentType | string): string {
    switch (type) {
      case 'Technique':
        return 'badge-technique';
      case 'Fonctionnelle':
        return 'badge-fonctionnelle';
      case 'Exploitation':
        return 'badge-exploitation';
      default:
        return '';
    }
  }

  handleView(document: Documentation): void {
    this.docService.viewFileInNewWindow(document.filename);
  }

  handleDownload(doc: Documentation): void {
    this.docService.downloadFile(doc.filename, doc.name);
  }

  ngOnInit() {
    this.findAllDocByAppId();
  }
}
