import { CategoryODAChild } from './category.type';
import { Classe } from './classe.type';
import { Cost, CreateCostWithoutApp } from './cost.type';
import { Departement } from './departement.type';
import {
  CreateDocumentationWithoutApp,
  Documentation,
} from './documentation.type';
import {
  CreateTechBusinessValueWithoutApp,
  TechBusinessValue,
} from './techBusinessValue.type';

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
  departement: Departement;
  classe: Classe;
  currentCost: Cost;
  currentTechBusinessValue: TechBusinessValue;
  costs: Cost[];
  techBusinessValues: TechBusinessValue[];
  isDeleted: boolean;
  documentations: Documentation[];
};

export type Time = 'tolerate' | 'invest' | 'migrate' | 'eliminate';
export type Status = 'development' | 'production' | 'decommissioned';

export type CreateApplication = {
  costWithoutApp: CreateCostWithoutApp;
  classeId: number;
  departementId: number;
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
  departementId: number;
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
  'costs' | 'techBusinessValues' | 'documentations'
> & {
  modifiedAt: Date;
  modifiedBy: string;
  appId: number;
  descriptionHistory: string;
};
