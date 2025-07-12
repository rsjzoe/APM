import { Component } from '@angular/core';
import { Application } from '../../application/app.type';
import { ApplicationService } from '../home/application.service';
import { CommonModule } from '@angular/common';
import { DateFormater } from '../../lib/dateFormater';
import { Observable } from 'rxjs';
import { UserService } from '../administration/user.service';

@Component({
  selector: 'app-corbeille',
  imports: [CommonModule],
  templateUrl: './corbeille.component.html',
  styleUrl: './corbeille.component.scss',
})
export class CorbeilleComponent {
  deletedApps: Application[] = [];
  loading = false;
  error: string | null = null;
  canEdit$!: Observable<boolean>;

  constructor(
    private appService: ApplicationService,
    public userService: UserService
  ) {}

  ngOnInit() {
    this.fetchDeletedApps();
    this.canEdit$ = this.userService.canEditService('application');
  }

  fetchDeletedApps() {
    this.loading = true;
    this.appService.findAllDeleted().subscribe({
      next: (apps) => {
        this.deletedApps = apps;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement.';
        this.loading = false;
      },
    });
  }

  restoreApp(id: number) {
    this.appService.restore(id).subscribe({
      next: () => {
        this.deletedApps = this.deletedApps.filter((app) => app.id !== id);
      },
      error: () => {
        this.error = 'Erreur lors de la restauration.';
      },
    });
  }

  formatDate(date: Date | string) {
    return DateFormater.format(date);
  }
}
