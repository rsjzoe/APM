import { Component } from '@angular/core';
import { ButtonComponent } from '../../components/button/button.component';
import { CommonModule } from '@angular/common';
import { Application } from '../../application/app.type';
import { applications } from '../../application/data';
import { ApplicationService } from './application.service';
import { ModalStateService } from './modal-add-app/modal-state.service';
import { IconDeleteComponent } from '../../components/icons/icon-delete/icon-delete.component';
import { IconEditComponent } from '../../components/icons/icon-edit/icon-edit.component';
import { Router } from '@angular/router';
import { ModalConfirmComponent } from '../../components/modal-confirm/modal-confirm.component';
import { ModalEditAppComponent } from './modal-edit-app/modal-edit-app.component';
import { ModalAddAppComponent } from './modal-add-app/modal-add-app.component';
import { UserService } from '../administration/user.service';
import { Role } from '../administration/user.type';

@Component({
  selector: 'app-home',
  imports: [
    ButtonComponent,
    CommonModule,
    IconDeleteComponent,
    IconEditComponent,
    ModalConfirmComponent,
    ModalEditAppComponent,
    ModalAddAppComponent,
  ],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  apps = applications;
  appIdDelete: number | null = null;

  constructor(
    private appService: ApplicationService,
    private modalStateService: ModalStateService,
    public userService: UserService,
    private router: Router
  ) {
    modalStateService.subscribeOnsubmit(this.refresh);
  }

  saveIdAppDelete = (id: number) => {
    this.appIdDelete = id;
  };

  onConfirmDelete = () => {
    if (this.appIdDelete) {
      this.deleteById(this.appIdDelete);
    }
  };

  goToDetail(id: number) {
    this.router.navigate(['/app-details', id]); // Redirection vers la page de détails
  }

  onClickAdd = () => {
    this.modalStateService.openAllFields();
  };

  findAll = () => {
    this.appService.findAll().subscribe({
      next: (data) => {
        this.apps = data;
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des tâches :', error);
      },
    });
  };

  deleteById = (id: number) => {
    this.appService.delete(id).subscribe({
      next: () => {
        this.apps = this.apps.filter((app) => app.id !== id);
      },
      error: (error) => {
        console.log('erreur de la suppresssion : ' + error);
      },
    });
  };

  editApp = (app: Application) => {
    this.modalStateService.editApp(app);
  };

  refresh = () => {
    this.findAll();
  };

  ngOnInit() {
    this.findAll();
  }

  getStatusColor(status: Application['status']): string {
    switch (status) {
      case 'development':
        return 'bg-warning';
      case 'production':
        return 'bg-success';
      case 'decommissioned':
        return 'bg-danger';
      default:
        return 'bg-secondary';
    }
  }
}
