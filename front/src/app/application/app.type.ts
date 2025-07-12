import { CategoryODAChild } from './category/category.type';
import { Classe } from './classe/classe.type';
import { Cost, CreateCostWithoutApp } from './cost/cost.type';
import { Departement } from './departement/departement.type';
import {
  CreateDocumentationWithoutApp,
  Documentation,
} from './documentation/documentation.type';
import {
  CreateTechBusinessValueWithoutApp,
  TechBusinessValue,
} from './tech-business-value/techBusinessValue.type';

export type Application = {
  id: number;
  name: string;
  description: string;
  startDate: Date;
  lastUpdate: Date;
  status: Status;
  time: Time;
  userTotal: number;
  noteBusinessValue: number | null;
  noteTechnicalDebt: number | null;
  category: CategoryODAChild;
  departements: Departement[];
  classe: Classe;
  currentCost: Cost;
  currentTechBusinessValue: TechBusinessValue;
  costs: Cost[];
  techBusinessValues: TechBusinessValue[];
  isDeleted: boolean;
  documentations: Documentation[];
  createdAt: Date;
  updatedAt: Date;
};

export type Time = 'tolerate' | 'invest' | 'migrate' | 'eliminate';
export type Status = 'development' | 'production' | 'decommissioned';

export type CreateApplication = {
  costWithoutApp: CreateCostWithoutApp;
  classeId: number;
  departementIds: number[];
  name: string;
  startDate: string | Date;
  status: Status;
  techBusinessValueWithoutApp: CreateTechBusinessValueWithoutApp;
  userTotal: number;
  categoryId: number;
  description: string;
  lastUpdate: string | Date;
  documentations: CreateDocumentationWithoutApp[];
};

export type UpdateApplication = Partial<{
  costWithoutApp: CreateCostWithoutApp;
  classeId: number;
  departementIds: number[];
  name: string;
  startDate: string | Date;
  status: Status;
  techBusinessValueWithoutApp: CreateTechBusinessValueWithoutApp;
  userTotal: number;
  categoryId: number;
  description: string;
  lastUpdate: string | Date;
  noteBusinessValue: number | null;
  noteTechnicalDebt: number | null;
  otherDescription: string | null;
}>;

export type AppHistory = Omit<
  Application,
  'costs' | 'techBusinessValues' | 'documentations' | 'createdAt' | 'updatedAt'
> & {
  modifiedAt: Date;
  modifiedBy: string;
  appId: number;
  descriptionHistory: string;
};
