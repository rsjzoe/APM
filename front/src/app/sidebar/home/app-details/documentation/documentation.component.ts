import { Component } from '@angular/core';
import { Document, DocumentType } from './document-type';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-documentation',
  imports: [CommonModule],
  templateUrl: './documentation.component.html',
  styleUrl: './documentation.component.scss',
  standalone: true,
})
export class DocumentationComponent {
  isOpen = false;
  documents: Document[] = [
    {
      id: '1',
      name: 'Guide utilisateur',
      type: 'Fonctionnelle',
      format: 'pdf',
      lastUpdated: new Date('2023-06-15'),
      size: '2.5 MB',
    },
    {
      id: '2',
      name: 'Spécifications techniques',
      type: 'Technique',
      format: 'docx',
      lastUpdated: new Date('2023-07-01'),
      size: '1.8 MB',
    },
    {
      id: '3',
      name: "Manuel d'installation",
      type: 'Exploitation',
      format: 'pdf',
      lastUpdated: new Date('2023-05-20'),
      size: '3.2 MB',
    },
    {
      id: '4',
      name: 'Architecture système',
      type: 'Technique',
      format: 'pptx',
      lastUpdated: new Date('2023-06-30'),
      size: '5.1 MB',
    },
    {
      id: '5',
      name: 'Guide de maintenance',
      type: 'Exploitation',
      format: 'pdf',
      lastUpdated: new Date('2023-07-10'),
      size: '1.5 MB',
    },
  ];

  get groupedDocuments(): Record<DocumentType, Document[]> {
    return this.documents.reduce((acc, doc) => {
      if (!acc[doc.type]) {
        acc[doc.type] = [];
      }
      acc[doc.type].push(doc);
      return acc;
    }, {} as Record<DocumentType, Document[]>);
  }

  togglePopover(): void {
    this.isOpen = !this.isOpen;
    console.log('togglePopover');
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

  handleView(document: Document): void {
    console.log('Viewing document:', document.name);
  }

  handleDownload(document: Document): void {
    console.log('Downloading document:', document.name);
  }
}
