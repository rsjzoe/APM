import { Injectable } from '@angular/core';
import {
  Application,
  CreateApplication,
  CreateDocumentation,
  DocumentationType,
  UpdateApplication,
} from '../../../application/appType';
import { ApplicationService } from '../application.service';
import { BudgetService } from '../../../application/budget.service';
import { forkJoin } from 'rxjs';

type Create = Omit<CreateApplication, 'documentations'> & {
  fonctionnelles: File[];
  techniques: File[];
  exploitation: File[];
};

type FieldState = {
  // Informations Générales
  name: boolean;
  description: boolean;
  userTotal: boolean;
  categoryODAChildId: boolean;
  departementId: boolean;
  classeId: boolean;

  // Valeurs et Coûts
  budgetBuild: boolean;
  budgetRun: boolean;
  businessValue: boolean;
  technicalDebt: boolean;

  // Date et Status
  startDate: boolean;
  lastUpdate: boolean;
  status: boolean;
  time: boolean;
};

@Injectable({
  providedIn: 'root',
})
export class ModalStateService {
  appEditing: Application | null = null;
  subscribersOnsubmit: (() => void)[] = [];

  // pour les checkbox
  openFields!: FieldState;

  createApplication: Create = {
    name: undefined!,
    description: undefined!,
    startDate: undefined!,
    lastUpdate: undefined!,
    status: undefined!,
    time: undefined!,
    userTotal: undefined!,
    categoryODAChildId: undefined!,
    departementId: undefined!,
    budget: {
      budgetBuild: undefined!,
      budgetRun: undefined!,
      businessValue: undefined!,
      createdAt: undefined!,
      technicalDebt: undefined!,
    },
    classeId: undefined!,
    exploitation: [],
    fonctionnelles: [],
    techniques: [],
  };

  constructor(
    private appService: ApplicationService,
    private budgetService: BudgetService
  ) {
    this.openAllFields();
  }

  resetData = () => {
    this.createApplication = {
      name: undefined!,
      description: undefined!,
      startDate: undefined!,
      lastUpdate: undefined!,
      status: undefined!,
      time: undefined!,
      userTotal: undefined!,
      categoryODAChildId: undefined!,
      departementId: undefined!,
      budget: {
        budgetBuild: undefined!,
        budgetRun: undefined!,
        businessValue: undefined!,
        createdAt: undefined!,
        technicalDebt: undefined!,
      },
      classeId: undefined!,
      exploitation: [],
      fonctionnelles: [],
      techniques: [],
    };
    this.appEditing = null;
    this.openAllFields();
  };

  setFieldsState = (value: boolean) => {
    this.openFields = {
      // Informations Générales
      name: value,
      description: value,
      userTotal: value,
      categoryODAChildId: value,
      departementId: value,
      classeId: value,

      // Valeurs et Coûts
      budgetBuild: value,
      budgetRun: value,
      businessValue: value,
      technicalDebt: value,

      // Date et Status
      startDate: value,
      lastUpdate: value,
      status: value,
      time: value,
    };
  };

  openAllFields = () => {
    this.setFieldsState(true);
  };

  hideAllFields = () => {
    this.setFieldsState(false);
  };

  editApp = (app: Application) => {
    this.appEditing = app;
    this.createApplication = {
      name: app.name,
      description: app.description,
      budget: {
        budgetBuild: app.budget.budgetBuild,
        budgetRun: app.budget.budgetRun,
        businessValue: app.budget.businessValue,
        createdAt: app.budget.createdAt,
        technicalDebt: app.budget.technicalDebt,
      },
      startDate: app.startDate,
      lastUpdate: app.lastUpdate,
      status: app.status,
      time: app.time,
      userTotal: app.userTotal,
      categoryODAChildId: app.categoryODAChild.id,
      departementId: app.departement.id,
      classeId: app.classe.id,
      exploitation: [],
      fonctionnelles: [],
      techniques: [],
    };    
    this.hideAllFields();
  };

  submit = () => {
    console.log('submit');
    console.log(this.createApplication);
    if (this.appEditing == null) {
      this.save();
    } else {
      this.updateApplication();
    }
  };

  transformCreateToCreateApplication(create: Create): CreateApplication {
    const documentations: CreateDocumentation[] = [
      ...create.fonctionnelles.map((file) => ({
        type: DocumentationType.fonctionnelle,
        file,
      })),
      ...create.techniques.map((file) => ({
        type: DocumentationType.technique,
        file,
      })),
      ...create.exploitation.map((file) => ({
        type: DocumentationType.exploitation,
        file,
      })),
    ];

    return {
      ...create,
      documentations,
    };
  }

  onChangeFonctionnelle(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement?.files && inputElement.files.length > 0) {
      this.createApplication.fonctionnelles = Array.from(inputElement.files);
    }
  }

  onChangeTechnique(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement?.files && inputElement.files.length > 0) {
      this.createApplication.techniques = Array.from(inputElement.files);
    }
  }

  onChangeExploitation(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement?.files && inputElement.files.length > 0) {
      this.createApplication.exploitation = Array.from(inputElement.files);
    }
  }

  save() {
    const data = this.transformCreateToCreateApplication(
      this.createApplication
    );
    this.appService.add(data).subscribe({
      next: (app) => {
        this.resetData();
        this.callSubscriberOnsubmit();
      },
    });
  }

  updateApplication = () => {
    if (this.appEditing == null) return;
    let updatedApp: UpdateApplication = {
      name: this.createApplication.name,
      description: this.createApplication.description,
      budget: {
        budgetBuild: this.createApplication.budget.budgetBuild,
        budgetRun: this.createApplication.budget.budgetRun,
        businessValue: this.createApplication.budget.businessValue,
        createdAt: this.createApplication.budget.createdAt,
        technicalDebt: this.createApplication.budget.technicalDebt,
        id: this.appEditing.budget.id,
      },
      startDate: this.createApplication.startDate,
      lastUpdate: this.createApplication.lastUpdate,
      status: this.createApplication.status,
      userTotal: this.createApplication.userTotal,
      categoryODAChildId: this.createApplication.categoryODAChildId,
      departementId: this.createApplication.departementId,
      classeId: this.createApplication.classeId,
      note: this.appEditing.note,
    };

    // mila vitaina izy roa vao miantso ny resetData sy ...
    forkJoin([
      this.budgetService.updateBugetById(this.appEditing.budget.id, {
        budgetBuild: this.createApplication.budget.budgetBuild,
        budgetRun: this.createApplication.budget.budgetRun,
        businessValue: this.createApplication.budget.businessValue,
        createdAt: this.createApplication.budget.createdAt,
        technicalDebt: this.createApplication.budget.technicalDebt,
      }),
      this.appService.update(this.appEditing.id, updatedApp),
    ]).subscribe({
      next: () => {
        this.resetData();
        this.callSubscriberOnsubmit();
      },
    });
  };

  callSubscriberOnsubmit() {
    for (let cb of this.subscribersOnsubmit) {
      cb();
    }
  }

  subscribeOnsubmit(cb: () => void) {
    this.subscribersOnsubmit.push(cb);
  }
}
