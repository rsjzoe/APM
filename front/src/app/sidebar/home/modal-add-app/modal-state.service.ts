import { Injectable } from '@angular/core';
import {
  Application,
  CreateApplication,
  UpdateApplication,
} from '../../../application-APM/appType';
import { ApplicationService } from '../application.service';

@Injectable({
  providedIn: 'root',
})
export class ModalStateService {
  constructor(private appService: ApplicationService) {}
  appEditing: Application | null = null;
  subscribersOnsubmit: (() => void)[] = [];

  createApplication: CreateApplication = {
    name: undefined!,
    description: undefined!,
    businessValue: undefined!,
    costBuild: undefined!,
    costRun: undefined!,
    startDate: undefined!,
    lastUpdate: undefined!,
    status: undefined!,
    time: undefined!,
    userTotal: undefined!,
    categoryId: undefined!,
    departementId: undefined!,
  };

  resetData() {
    this.createApplication = {
      name: undefined!,
      description: undefined!,
      businessValue: undefined!,
      costBuild: undefined!,
      costRun: undefined!,
      startDate: undefined!,
      lastUpdate: undefined!,
      status: undefined!,
      time: undefined!,
      userTotal: undefined!,
      categoryId: undefined!,
      departementId: undefined!,
    };
    this.appEditing = null;
  }

  editApp = (app: Application) => {
    this.appEditing = app;
    this.createApplication = {
      name: app.name,
      description: app.description,
      businessValue: app.businessValue,
      costBuild: app.costBuild,
      costRun: app.costRun,
      startDate: app.startDate,
      lastUpdate: app.lastUpdate,
      status: app.status,
      time: app.time,
      userTotal: app.userTotal,
      categoryId: app.category.id,
      departementId: app.departement.id,
    };
  };

  submit = () => {
    if (this.appEditing == null) {
      this.save();
    } else {
      this.updateApplication();
    }
  };

  save() {
    this.appService.add(this.createApplication).subscribe({
      next: (app) => {
        this.resetData();
        this.callSubscriberOnsubmit()
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
      note: this.appEditing.note,
    };

    this.appService.update(this.appEditing.id, updatedApp).subscribe({
      next: (app) => {
        // this.refresh();
        this.resetData();
        this.callSubscriberOnsubmit()
      },
    });
  };

  callSubscriberOnsubmit(){
    for(let cb of this.subscribersOnsubmit){
      cb()
    }
  }

  subscribeOnsubmit(cb:(()=>void)){
    this.subscribersOnsubmit.push(cb)
  }
}
