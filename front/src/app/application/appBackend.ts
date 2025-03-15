import {
  AppHistory,
  Application,
  Budget,
  CategoryODAChild,
  CategoryODAParent,
  Classe,
  CreateApplication,
  CreateBudget,
  CreateCategoryODAChild,
  CreateCategoryODAParent,
  CreateDocumentation,
  Departement,
  Documentation,
  DocumentationType,
  Status,
  Time,
  UpdateApplication,
  UpdateBudget,
  UpdateCategoryODAChild,
  UpdateCategoryODAParent,
} from './appType';

export type AppBackend = {
  applicationId: number;
  applicationName: string;
  applicationDescription: string;
  numberUsers: number;
  creationDate: Date;
  status: Status;
  time: Time;
  note: number;
  budget: BudgetBackend;
  departement: DepartementBackend;
  isActive: boolean;
  categoryODAChild: CategoryODAChildBackend;
  classe: ClasseBackend;
  documentations: DocumentationBackend[];
};

export type AppDetailsBackend = {
  application: Omit<AppBackend, 'budget'>;
  budget: BudgetBackend;
};

export type AppHistoryBackend = {
  application: Omit<AppBackend, 'budget'>;
  description: string;
  action: string;
  modifiedAt: Date;
  modifiedBy: string;
};

export type DocumentationBackend = {
  id: number;
  name: string;
  filename: string;
  url: string;
  type: DocumentationType;
};

export type CreateDocumentationBackend = {
  type: DocumentationType;
  file: File;
};

export type ClasseBackend = {
  id: number;
  name: string;
};

export type BudgetBackend = {
  budgetId: number;
  businessValue: number;
  budgetBuild: number;
  budgetRun: number;
  technicalDebt: number;
  createdAt: string;
};

export type BudgetHistoryBackend = {
  application: AppBackend;
} & BudgetBackend;

export type CreateBudgetBackend = Omit<BudgetBackend, 'budgetId'>;
export type UpdateBudgetBackend = Omit<BudgetBackend, 'budgetId'>;

export type CategoryODAParentBackend = {
  id: number;
  name: string;
  bgColor: string;
  childs: CategoryODAChildBackend[];
};

export type CategoryODAChildBackend = {
  id: number;
  name: string;
  isDelete: boolean;
  parentId: number;
  parentName: string;
};

export type DepartementBackend = {
  departementId: number;
  departementName: string;
};

export type CreateAppBackend = {
  budget: CreateBudgetBackend;
  application: Omit<
    AppBackend,
    | 'applicationId'
    | 'departement'
    | 'categorie'
    | 'note'
    | 'time'
    | 'budget'
    | 'categoryODAChild'
    | 'classe'
    | 'documentations'
  > & {
    categoryId: number;
    classeId: number;
    departementId: number;
    dateUpdate: Date;
    categoryODAChildId: number;
    documentations: CreateDocumentationBackend[];
  };
};

export type UpdateAppBackend = Omit<
  AppBackend,
  | 'applicationId'
  | 'departement'
  | 'categorie'
  | 'time'
  | 'categoryODAChild'
  | 'classe'
  | 'budget'
  | 'isActive'
  | 'documentations'
> & {
  departementId: number;
  classeId: number;
  categoryODAChildId: number;
  dateUpdate: Date;
};

export type CreateCategoryODAChildBack = {
  parentId: number;
  name: string;
};

export type CreateCategoryODAParentBack = {
  name: string;
  bgColor: string;
};

export type UpdateCategoryODAParentBack = {
  name: string;
  bgColor: string;
};

export type UpdateCategoryODAChildBack = {
  parentId: number;
  name: string;
};

export function transformCreateBudgetToCreateBudgetBackend(
  createBudget: CreateBudget
): CreateBudgetBackend {
  return {
    businessValue: createBudget.businessValue,
    budgetBuild: createBudget.budgetBuild,
    budgetRun: createBudget.budgetRun,
    technicalDebt: createBudget.technicalDebt,
    createdAt: createBudget.createdAt,
  };
}
export function transformUpdateBudgetToUpdateBudgetBackend(
  updateBudget: UpdateBudget
): UpdateBudgetBackend {
  return {
    businessValue: updateBudget.businessValue,
    budgetBuild: updateBudget.budgetBuild,
    budgetRun: updateBudget.budgetRun,
    technicalDebt: updateBudget.technicalDebt,
    createdAt: updateBudget.createdAt,
  };
}

export function transformBudgetHistoryBackendToBudget(
  budgetHistoryBackend: BudgetHistoryBackend
): Budget {
  return {
    id: budgetHistoryBackend.budgetId,
    businessValue: budgetHistoryBackend.businessValue,
    budgetBuild: budgetHistoryBackend.budgetBuild,
    budgetRun: budgetHistoryBackend.budgetRun,
    technicalDebt: budgetHistoryBackend.technicalDebt,
    createdAt: budgetHistoryBackend.createdAt,
  };
}

export function transformAppDetailsBackendToApplication(
  appDetailsBackend: AppDetailsBackend
): Application {
  return {
    id: appDetailsBackend.application.applicationId,
    name: appDetailsBackend.application.applicationName,
    description: appDetailsBackend.application.applicationDescription,
    startDate: appDetailsBackend.application.creationDate,
    lastUpdate: new Date(),
    status: appDetailsBackend.application.status,
    time: appDetailsBackend.application.time,
    userTotal: appDetailsBackend.application.numberUsers,
    note: appDetailsBackend.application.note,
    departement: {
      id: appDetailsBackend.application.departement.departementId,
      name: appDetailsBackend.application.departement.departementName,
    },
    categoryODAChild: transformCategoryODAChildBackendToCategoryODAChild(
      appDetailsBackend.application.categoryODAChild
    ),
    classe: {
      id: appDetailsBackend.application.classe.id,
      name: appDetailsBackend.application.classe.name,
    },
    budget: {
      id: appDetailsBackend.budget.budgetId,
      businessValue: appDetailsBackend.budget.businessValue,
      budgetBuild: appDetailsBackend.budget.budgetBuild,
      budgetRun: appDetailsBackend.budget.budgetRun,
      technicalDebt: appDetailsBackend.budget.technicalDebt,
      createdAt: appDetailsBackend.budget.createdAt,
    },
    documentations: appDetailsBackend.application.documentations.map(
      transformDocumentationBackendToDocumentation
    ),
  };
}

export function transformDocumentationBackendToDocumentation(
  documentationBackend: DocumentationBackend
): Documentation {
  return {
    id: documentationBackend.id,
    name: documentationBackend.name,
    url: documentationBackend.url,
    filename: documentationBackend.filename,
    type: documentationBackend.type,
  };
}

export function transformClasseBackendToClasse(
  classeBackend: ClasseBackend
): Classe {
  return {
    id: classeBackend.id,
    name: classeBackend.name,
  };
}

export function transformCreateCategoryODAChildToBackend(
  createCategoryODAChild: CreateCategoryODAChild
): CreateCategoryODAChildBack {
  return {
    parentId: createCategoryODAChild.parentId,
    name: createCategoryODAChild.name,
  };
}

export function transformCreateCategoryODAParentToBackend(
  createCategoryODAParent: CreateCategoryODAParent
): CreateCategoryODAParentBack {
  return {
    name: createCategoryODAParent.name,
    bgColor: createCategoryODAParent.bgColor,
  };
}

export function transformUpdateCategoryODAParentToBackend(
  updateCategoryODAParent: UpdateCategoryODAParent
): UpdateCategoryODAParentBack {
  return {
    name: updateCategoryODAParent.name,
    bgColor: updateCategoryODAParent.bgColor,
  };
}

export function transformUpdateCategoryODAChildToBackend(
  updateCategoryODAChild: UpdateCategoryODAChild
): UpdateCategoryODAChildBack {
  return {
    parentId: updateCategoryODAChild.parentId,
    name: updateCategoryODAChild.name,
  };
}

export function transformAppBackendToApplication(
  appBackend: AppBackend
): Application {
  return {
    id: appBackend.applicationId,
    name: appBackend.applicationName,
    description: appBackend.applicationDescription,
    startDate: appBackend.creationDate,
    lastUpdate: new Date(),
    status: appBackend.status,
    time: appBackend.time,
    userTotal: appBackend.numberUsers,
    note: appBackend.note,
    departement: {
      id: appBackend.departement.departementId,
      name: appBackend.departement.departementName,
    },
    categoryODAChild: transformCategoryODAChildBackendToCategoryODAChild(
      appBackend.categoryODAChild
    ),
    classe: {
      id: appBackend.classe.id,
      name: appBackend.classe.name,
    },
    budget: {
      id: appBackend.budget?.budgetId,
      businessValue: appBackend.budget?.businessValue,
      budgetBuild: appBackend.budget?.budgetBuild,
      budgetRun: appBackend.budget?.budgetRun,
      technicalDebt: appBackend.budget?.technicalDebt,
      createdAt: appBackend.budget?.createdAt,
    },
    documentations: appBackend.documentations.map(
      transformDocumentationBackendToDocumentation
    ),
  };
}

export function transformCategoryODAChildToBackend(
  categoryODAChild: CategoryODAChild
): CategoryODAChildBackend {
  return {
    id: categoryODAChild.id,
    name: categoryODAChild.name,
    isDelete: categoryODAChild.isDelete,
    parentId: categoryODAChild.parentId,
    parentName: categoryODAChild.parentName,
  };
}

export function transformCategoryODAParentBackendToCategoryODAParent(
  categoryODAParentBackend: CategoryODAParentBackend
): CategoryODAParent {
  return {
    id: categoryODAParentBackend.id,
    name: categoryODAParentBackend.name,
    bgColor: categoryODAParentBackend.bgColor,
    childs: categoryODAParentBackend.childs.map(
      transformCategoryODAChildBackendToCategoryODAChild
    ),
  };
}

export function transformCategoryODAChildBackendToCategoryODAChild(
  categoryODAChildBackend: CategoryODAChildBackend
): CategoryODAChild {
  return {
    id: categoryODAChildBackend.id,
    name: categoryODAChildBackend.name,
    isDelete: categoryODAChildBackend.isDelete,
    parentId: categoryODAChildBackend.parentId,
    parentName: categoryODAChildBackend.parentName,
  };
}

export function transformCreateApplicationToCreateAppBackend(
  createApplication: CreateApplication
): CreateAppBackend {
  return {
    budget: {
      businessValue: createApplication.budget.businessValue,
      budgetBuild: createApplication.budget.budgetBuild,
      budgetRun: createApplication.budget.budgetRun,
      technicalDebt: createApplication.budget.technicalDebt,
      createdAt: createApplication.budget.createdAt,
    },
    application: {
      applicationName: createApplication.name,
      applicationDescription: createApplication.description,
      numberUsers: createApplication.userTotal,
      creationDate: createApplication.startDate,
      status: createApplication.status,
      categoryId: createApplication.categoryODAChildId,
      classeId: createApplication.classeId,
      departementId: createApplication.departementId,
      dateUpdate: createApplication.lastUpdate,
      categoryODAChildId: createApplication.categoryODAChildId,
      isActive: true,
      documentations: createApplication.documentations.map(
        transformCreateDocumentationToCreateDocumentationBackend
      ),
    },
  };
}

export function transformCreateDocumentationToCreateDocumentationBackend(
  createDocumentation: CreateDocumentation
): CreateDocumentationBackend {
  return {
    type: createDocumentation.type,
    file: createDocumentation.file,
  };
}

export function categoryBackendToCategory(
  categoryBackend: CategoryODAChildBackend
): CategoryODAChild {
  return {
    id: categoryBackend.id,
    name: categoryBackend.name,
    isDelete: categoryBackend.isDelete,
    parentId: categoryBackend.parentId,
    parentName: categoryBackend.parentName,
  };
}

export function departementBackendToDepartement(
  departementBackend: DepartementBackend
): Departement {
  return {
    id: departementBackend.departementId,
    name: departementBackend.departementName,
  };
}

export function transformUpdateApplicationToBackend(
  updateApplication: UpdateApplication
): UpdateAppBackend {
  return {
    applicationName: updateApplication.name,
    applicationDescription: updateApplication.description,
    numberUsers: updateApplication.userTotal,
    creationDate: updateApplication.startDate,
    status: updateApplication.status,
    departementId: updateApplication.departementId,
    categoryODAChildId: updateApplication.categoryODAChildId,
    dateUpdate: updateApplication.lastUpdate,
    classeId: updateApplication.classeId,
    note: updateApplication.note,
  };
}

export function transformAppHistoryBackendToAppHistory(
  appHistoryBackend: AppHistoryBackend
): AppHistory {
  return {
    id: appHistoryBackend.application.applicationId,
    name: appHistoryBackend.application.applicationName,
    description: appHistoryBackend.action,
    startDate: appHistoryBackend.application.creationDate,
    lastUpdate: new Date(),
    status: appHistoryBackend.application.status,
    time: appHistoryBackend.application.time,
    userTotal: appHistoryBackend.application.numberUsers,
    note: appHistoryBackend.application.note,
    departement: {
      id: appHistoryBackend.application.departement.departementId,
      name: appHistoryBackend.application.departement.departementName,
    },
    categoryODAChild: transformCategoryODAChildBackendToCategoryODAChild(
      appHistoryBackend.application.categoryODAChild
    ),
    classe: {
      id: appHistoryBackend.application.classe.id,
      name: appHistoryBackend.application.classe.name,
    },
    documentations: appHistoryBackend.application.documentations.map(
      transformDocumentationBackendToDocumentation
    ),
    budget: {
      id: undefined!,
      businessValue: undefined!,
      budgetBuild: undefined!,
      budgetRun: undefined!,
      technicalDebt: undefined!,
      createdAt: undefined!,
    },
    modifiedAt: appHistoryBackend.modifiedAt,
    modifiedBy: appHistoryBackend.modifiedBy,
    applicationId: appHistoryBackend.application.applicationId,
  };
}
