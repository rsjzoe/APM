import { Injectable } from '@angular/core';
import {
  Application,
  CreateApplication,
  UpdateApplication,
} from '../../../application/app.type';
import { ApplicationService } from '../application.service';
import {
  CreateDocumentationWithoutApp,
  DocumentationType,
} from '../../../application/documentation/documentation.type';
import { Departement } from '../../../application/departement/departement.type';
import * as bootstrap from 'bootstrap';

type Create = Omit<CreateApplication, 'documentations' | 'departementIds'> & {
  fonctionnelles: File[];
  techniques: File[];
  exploitation: File[];
  selectedDepartements: Departement[];
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
    userTotal: undefined!,
    categoryId: undefined!,
    selectedDepartements: [],
    costWithoutApp: {
      costBuild: undefined!,
      costRun: undefined!,
    },
    techBusinessValueWithoutApp: {
      businessValue: undefined!,
      technicalDebt: undefined!,
    },
    classeId: undefined!,
    exploitation: [],
    fonctionnelles: [],
    techniques: [],
    // selectedDepartements: Departement[] = [];
  };

  otherDescription!: string;

  constructor(private appService: ApplicationService) {
    this.openAllFields();
  }

  resetData = () => {
    this.createApplication = {
      name: undefined!,
      description: undefined!,
      startDate: undefined!,
      lastUpdate: undefined!,
      status: undefined!,
      userTotal: undefined!,
      categoryId: undefined!,
      selectedDepartements: [],
      costWithoutApp: {
        costBuild: undefined!,
        costRun: undefined!,
      },
      techBusinessValueWithoutApp: {
        businessValue: undefined!,
        technicalDebt: undefined!,
      },
      classeId: undefined!,
      exploitation: [],
      fonctionnelles: [],
      techniques: [],
    };
    this.appEditing = null;
    this.openAllFields();
    this.otherDescription = undefined!;
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
      costWithoutApp: {
        costBuild: app.currentCost.costBuild,
        costRun: app.currentCost.costRun,
      },
      techBusinessValueWithoutApp: {
        businessValue: app.currentTechBusinessValue.businessValue,
        technicalDebt: app.currentTechBusinessValue.technicalDebt,
      },
      startDate: app.startDate,
      lastUpdate: app.lastUpdate,
      status: app.status,
      userTotal: app.userTotal,
      categoryId: app.category.id,
      selectedDepartements: app.departements,
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
    const documentations: CreateDocumentationWithoutApp[] = [
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

    const { selectedDepartements, ...rest } = create;

    return {
      ...rest,
      departementIds: selectedDepartements.map((dept) => dept.id),
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
        const toastEl = document.getElementById('liveToast')!;
        const toast = new bootstrap.Toast(toastEl);
        toast.show();
      },
    });
  }

  updateApplication = () => {
    if (this.appEditing == null) return;
    let updatedApp: UpdateApplication = {
      name: this.createApplication.name,
      description: this.createApplication.description,
      costWithoutApp: {
        costBuild: this.createApplication.costWithoutApp.costBuild,
        costRun: this.createApplication.costWithoutApp.costRun,
      },
      techBusinessValueWithoutApp: {
        businessValue:
          this.createApplication.techBusinessValueWithoutApp.businessValue,
        technicalDebt:
          this.createApplication.techBusinessValueWithoutApp.technicalDebt,
      },
      startDate: this.createApplication.startDate,
      lastUpdate: this.createApplication.lastUpdate,
      status: this.createApplication.status,
      userTotal: this.createApplication.userTotal,
      categoryId: this.createApplication.categoryId,
      departementIds: this.createApplication.selectedDepartements.map(
        (dept) => dept.id
      ),
      classeId: this.createApplication.classeId,
      noteBusinessValue: this.appEditing.noteBusinessValue,
      noteTechnicalDebt: this.appEditing.noteTechnicalDebt,
      otherDescription: this.otherDescription,
    };

    this.appService.update(this.appEditing.id, updatedApp).subscribe({
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
