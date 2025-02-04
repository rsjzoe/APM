import { Component, Input, ViewEncapsulation } from '@angular/core';
import { ModalGeneralComponent } from './modal-general/modal-general.component';
import { ModalStatusDateComponent } from './modal-status-date/modal-status-date.component';
import { ModalValeurCoutComponent } from './modal-valeur-cout/modal-valeur-cout.component';
import {
  Application,
  Category,
  CreateApplication,
  UpdateApplication,
} from '../../../application-APM/appType';
import { ValueFromCost, ValueFromGeneral, ValueFromStatus } from './modal-type';
import { ApplicationService } from '../home.service';

@Component({
  selector: 'app-modal-add-app',
  imports: [
    ModalGeneralComponent,
    ModalStatusDateComponent,
    ModalValeurCoutComponent,
  ],
  templateUrl: './modal-add-app.component.html',
  styleUrl: './modal-add-app.component.scss',
  encapsulation: ViewEncapsulation.None,
})
export class ModalAddAppComponent {
  createApplication!: CreateApplication;
  @Input() appEditing: Application | null = null;
  @Input() refresh = () => {};

  constructor(private appService: ApplicationService) {}

  getValueFromGeneral = (value: ValueFromGeneral) => {
    this.createApplication = { ...this.createApplication, ...value };
    // this.application = {
    //   name: this.application.name,
    //   description: this.application.description,
    //   category: this.application.category,
    //   userTeam: this.application.userTeam,
    //   businessValue: this.application.businessValue,
    //   costBuild: this.application.costBuild,
    //   costRun: this.application.costRun,
    //   lastUpdate: this.application.lastUpdate,
    //   performance: this.application.performance,
    //   startDate: this.application.startDate,
    //   status: this.application.status,
    //   time: this.application.time,
    //   userTotal: this.application.userTotal,
    //   name: value.name,
    //   description: value.description,
    //   category: value.category,
    //   userTeam: value.userTeam,
    // };
  };

  getValueFromCost = (value: ValueFromCost) => {
    this.createApplication = { ...this.createApplication, ...value };
  };

  getValueFromStatus = (value: ValueFromStatus) => {
    this.createApplication = { ...this.createApplication, ...value };
    if (this.appEditing == null) {
      this.save();
    } else {
      this.updateApplication();
    }
  };

  save() {
    console.log(this.createApplication);

    this.appService.add(this.createApplication).subscribe({
      next: (app) => {
        this.refresh();
      },
    });
  }

  updateApplication = () => {
    if (this.appEditing == null) return;
    let updatedApp: UpdateApplication = {
      name: this.createApplication.name,
      description: this.createApplication.description,
      businessValue: this.createApplication.businessValue,
      costBuild: this.createApplication.costBuild,
      costRun: this.createApplication.costRun,
      startDate: this.createApplication.startDate,
      lastUpdate: this.createApplication.lastUpdate,
      status: this.createApplication.status,
      time: this.createApplication.time,
      userTotal: this.createApplication.userTotal,
      categoryId: this.createApplication.categoryId,
      departementId: this.createApplication.departementId,
      note: this.appEditing.note
    };
    this.appService.update(this.appEditing.id, updatedApp).subscribe({
      next: (app) => {
        this.refresh();
      },
    });
  };
}
