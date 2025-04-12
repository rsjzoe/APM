export type Documentation = {
  id: number;
  name: string;
  url: string;
  filename: string;
  type: DocumentationType;
};

export type CreateDocumentation = {
  type: DocumentationType;
  file: File;
  applicationId: string;
};

export type CreateDocumentationWithoutApp = {
  type: DocumentationType;
  file: File;
};

export enum DocumentationType {
  fonctionnelle = 'fonctionnelle',
  technique = 'technique',
  exploitation = 'exploitation',
}
