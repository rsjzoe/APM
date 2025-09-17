import { Component } from '@angular/core';
import { ButtonComponent } from '../../components/button/button.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import {
  Application,
  ApplicationQuery,
  PaginationOutput,
} from '../../application/app.type';
import { DepartementService } from '../../application/departement/departement.service';
import { Departement } from '../../application/departement/departement.type';
import { ApplicationService } from './application.service';
import { ModalStateService } from './modal-add-app/modal-state.service';
import { IconDeleteComponent } from '../../components/icons/icon-delete/icon-delete.component';
import { Router } from '@angular/router';
import { ModalConfirmComponent } from '../../components/modal-confirm/modal-confirm.component';
import { ModalEditAppComponent } from './modal-edit-app/modal-edit-app.component';
import { ModalAddAppComponent } from './modal-add-app/modal-add-app.component';
import { UserService } from '../administration/user.service';
import { combineLatest, map, Observable } from 'rxjs';
import { DateFormater } from '../../lib/dateFormater';
import { SocketService } from '../../socket.service';

@Component({
  selector: 'app-home',
  imports: [
    ButtonComponent,
    CommonModule,
    IconDeleteComponent,
    ModalConfirmComponent,
    ModalEditAppComponent,
    ModalAddAppComponent,
    FormsModule,
  ],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  departements: Departement[] = [];
  apps: PaginationOutput<Application> = {
    items: [],
    totalPages: 1,
    currentPage: 1,
    totalItems: 0,
    pageSize: 0,
  };
  appIdDelete: number | null = null;
  canAddApp$!: Observable<boolean>;
  canEdit$!: Observable<boolean>;
  canDelete$!: Observable<boolean>;
  canEditOrDelete$!: Observable<boolean>;

  years: number[] = [];
  query: ApplicationQuery = {
    year: null,
    departementId: null,
    page: 1,
    size: 8,
  };

  constructor(
    private appService: ApplicationService,
    private modalStateService: ModalStateService,
    public userService: UserService,
    private router: Router,
    private departementService: DepartementService,
    private socketService: SocketService
  ) {
    this.socketService.onEvent('refetch_app', () => {
      // find application
      this.init();
    });
    modalStateService.subscribeOnsubmit(this.refresh);
    this.years = this.getYearsList();
  }

  getYearsList(): number[] {
    const currentYear = new Date().getFullYear();
    return Array.from({ length: 10 }, (_, i) => currentYear - i);
  }

  onYearChange(e: unknown) {
    this.findAll();
  }

  onSearchChange() {
    this.findAll();
  }

  onDepartementChange(e: unknown) {
    this.findAll();
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
    this.appService.findAll(this.query).subscribe({
      next: (data) => {
        this.apps = data;
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des tâches :', error);
      },
    });
  };

  goToPrevPage() {
    if (this.apps.currentPage > 1) {
      this.query.page = this.apps.currentPage - 1;
      this.findAll();
    }
  }

  goToNextPage() {
    if (this.apps.currentPage < this.apps.totalPages) {
      this.query.page = this.apps.currentPage + 1;
      this.findAll();
    }
  }

  deleteById = (id: number) => {
    this.appService.delete(id).subscribe({
      next: () => {
        this.findAll();
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

  init() {
    this.findAll();
    this.canAddApp$ = this.userService.canCreateService('application');
    this.canEdit$ = this.userService.canEditService('application');
    this.canDelete$ = this.userService.canDeleteService('application');
    this.canEditOrDelete$ = combineLatest([
      this.canDelete$,
      this.canEdit$,
    ]).pipe(map(([canDelete, canEdit]) => canDelete || canEdit));
    this.departementService.findAll().subscribe({
      next: (data) => {
        this.departements = data;
      },
    });
  }

  ngOnInit() {
    this.init();
  }

  formatDate(date: Date | string) {
    return DateFormater.format(date);
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
