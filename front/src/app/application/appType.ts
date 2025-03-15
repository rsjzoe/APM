export type Application = {
  id: number;
  name: string;
  description: string;
  startDate: Date;
  lastUpdate: Date;
  status: Status;
  time: Time;
  userTotal: number;
  note: number;
  departement: Departement;
  categoryODAChild: CategoryODAChild;
  classe: Classe;
  budget: Budget;
  documentations: Documentation[];
};

export type Classe = {
  id: number;
  name: string;
};

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
};

export enum DocumentationType {
  fonctionnelle = 'fonctionnelle',
  technique = 'technique',
  exploitation = 'exploitation',
}

export type Budget = {
  id: number;
  businessValue: number;
  budgetBuild: number;
  budgetRun: number;
  technicalDebt: number;
  createdAt: string;
};

export type CreateBudget = Omit<Budget, 'id'>;
export type UpdateBudget = Omit<Budget, 'id'>;

export type CategoryODAParent = {
  id: number;
  name: string;
  bgColor: string;
  childs: CategoryODAChild[];
};

export type CategoryODAChild = {
  id: number;
  name: string;
  isDelete: boolean;
  parentId: number;
  parentName: string;
};

export type CreateCategoryODAChild = {
  parentId: number;
  name: string;
};

export type CreateCategoryODAParent = {
  name: string;
  bgColor: string;
};

export type UpdateCategoryODAParent = {
  name: string;
  bgColor: string;
};

export type UpdateCategoryODAChild = {
  parentId: number;
  name: string;
};

export type Departement = {
  id: number;
  name: string;
};
export type Time = 'tolerate' | 'invest' | 'migrate' | 'eliminate';
export type Status = 'development' | 'production' | 'decommissioned';

export type QuestionGroupe = {
  id: number;
  text: string;
  borderColor: string;
  coeff: number;
  questions: Question[];
  type: 'technical debt' | 'business value';
};

export type Question = {
  id: number;
  text: string;
};

export type CreateApplication = Omit<
  Application,
  | 'id'
  | 'note'
  | 'category'
  | 'departement'
  | 'budget'
  | 'categoryODAChild'
  | 'classe'
  | 'documentations'
> & {
  departementId: number;
  classeId: number;
  budget: CreateBudget;
  categoryODAChildId: number;
  documentations: CreateDocumentation[];
};

export type UpdateApplication = Omit<
  Application,
  | 'id'
  | 'category'
  | 'departement'
  | 'categoryODAChild'
  | 'time'
  | 'classe'
  | 'documentations'
> & {
  classeId: number;
  categoryODAChildId: number;
  departementId: number;
};

export type AppHistory = Application & {
  modifiedAt: Date;
  modifiedBy: string;
  applicationId: number;
};

export type CreateQuestionGroupe = Omit<QuestionGroupe, 'id'>;
export type CreateQuestion = Omit<Question, 'id'>;
export type UpdateQuestionGroupe = Omit<QuestionGroupe, 'id'>;
export type UpdateQuestion = Omit<Question, 'id'>;
