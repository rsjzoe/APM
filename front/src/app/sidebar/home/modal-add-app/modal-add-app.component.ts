import { Component, Input, ViewEncapsulation } from '@angular/core';
import { ModalGeneralComponent } from './modal-general/modal-general.component';
import { ModalStatusDateComponent } from './modal-status-date/modal-status-date.component';
import { ModalValeurCoutComponent } from './modal-valeur-cout/modal-valeur-cout.component';
import { Category, CreateApplication } from '../../../application-APM/appType';
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
  application!: CreateApplication;
  @Input() refresh = () => {};

  constructor(private appService: ApplicationService) {}

  getValueFromGeneral = (value: ValueFromGeneral) => {

    this.application = { ...this.application, ...value };
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
    this.application = { ...this.application, ...value };
  };

  getValueFromStatus = (value: ValueFromStatus) => {
    this.application = { ...this.application, ...value };
    this.save();
  };

  save() {
    console.log(this.application);
    
    this.appService.add(this.application).subscribe({
      next: (app) => {
        this.refresh();
      },
    });
  }
}
