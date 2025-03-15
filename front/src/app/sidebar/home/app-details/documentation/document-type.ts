export type DocumentType = 'Technique' | 'Fonctionnelle' | 'Exploitation';

export interface Document {
  id: string;
  name: string;
  type: DocumentType;
  format: string;
  lastUpdated: Date;
  size: string;
}
