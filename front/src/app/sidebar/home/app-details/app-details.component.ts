import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { IconStarComponent } from '../../../components/icons/icon-star/icon-star.component';
import { IconDollarComponent } from '../../../components/icons/icon-dollar/icon-dollar.component';
import { IconUserComponent } from '../../../components/icons/icon-user/icon-user.component';
import { IconCategoryComponent } from '../../../components/icons/icon-category/icon-category.component';
import { IconCalendarComponent } from '../../../components/icons/icon-calendar/icon-calendar.component';
import { IconUpdateComponent } from '../../../components/icons/icon-update/icon-update.component';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AppHistory, Application, Budget } from '../../../application/appType';
import { ApplicationService } from '../application.service';
import { CommonModule } from '@angular/common';
import { IconLifecycleComponent } from '../../../components/icons/icon-lifecycle/icon-lifecycle.component';
import { TimelineHistoryComponent } from './timeline-history/timeline-history.component';
import { TimelineComponent } from '../../../components/timeline/timeline.component';
import { RateApplicationComponent } from './rate-application/rate-application.component';
import { DateFormater } from '../../../lib/dateFormater';
import { AppHistoryService } from '../../../application/app-history.service';
import { NumberFormat } from '../../../lib/numberFormat';
import { DocumentationComponent } from './documentation/documentation.component';
import { BudgetService } from '../../../application/budget.service';
import { ButtonComponent } from '../../../components/button/button.component';
import { IconClassificationComponent } from '../../../components/icons/icon-classification/icon-classification.component';
import { IconSablierComponent } from '../../../components/icons/icon-sablier/icon-sablier.component';
import { UserService } from '../../administration/user.service';
import { Role } from '../../administration/user.type';
import { DashboardComponent } from './dashboard/dashboard.component';
import { IconStatusComponent } from '../../../components/icons/icon-status/icon-status.component';

@Component({
  selector: 'app-app-details',
  imports: [
    IconStarComponent,
    IconDollarComponent,
    IconUserComponent,
    IconCategoryComponent,
    IconCalendarComponent,
    IconUpdateComponent,
    CommonModule,
    IconLifecycleComponent,
    TimelineComponent,
    TimelineHistoryComponent,
    RateApplicationComponent,
    RouterLink,
    DocumentationComponent,
    ButtonComponent,
    IconClassificationComponent,
    IconSablierComponent,
    DashboardComponent,
    IconStatusComponent,
  ],
  templateUrl: './app-details.component.html',
  styleUrl: './app-details.component.scss',
})
export class AppDetailsComponent {
  @Input() route!: string;
  @ViewChild('chartCanvasTechBusiness')
  chartCanvasTechBusiness!: ElementRef<HTMLCanvasElement>;
  @ViewChild('chartCanvasCost') chartCanvasCost!: ElementRef<HTMLCanvasElement>;

  application: Application | null = null;
  appId: number | null = null;
  appHistory: AppHistory[] = [];
  budgetHistory: Budget[] = [];
  activeTab: string = 'dashboard';

  constructor(
    private appService: ApplicationService,
    private activateRoute: ActivatedRoute,
    private appHistoryService: AppHistoryService,
    private budgetService: BudgetService,
    public userService: UserService,
    private router: Router
  ) {}

  canAddDoc() {
    return (
      this.userService.getUserConnected()?.role == Role.admin ||
      this.userService.getUserConnected()?.role == Role.editor
    );
  }

  numberFormat = (value: number) => {
    return NumberFormat.formatDevise(value);
  };

  setActiveTab(tab: string) {
    this.activeTab = tab;
  }

  formatDate(date: Date | string) {
    return DateFormater.format(date);
  }

  ngOnInit() {
    this.appId = Number(this.activateRoute.snapshot.paramMap.get('id'));
    this.findAppById(this.appId);
    this.findAllAppHistory(this.appId);
  }

  findAppById(id: number) {
    this.appService.findById(id).subscribe({
      next: (val) => {
        if (val == null) {
          this.router.navigate(['/404']);
        } else {
          this.application = val;
        }
      },
    });
  }

  findAllAppHistory = (appId: number) => {
    this.appHistoryService.findAllByAppId(appId).subscribe({
      next: (data) => {
        this.appHistory = data;
        // this.chartData = this.generateChartData(data);
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des tâches :', error);
      },
    });
  };

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

  getTimeColor(time: Application['time']): string {
    switch (time) {
      case 'tolerate':
        return 'text-warning';
      case 'invest':
        return 'text-success';
      case 'migrate':
        return 'text-info';
      case 'eliminate':
        return 'text-danger';
      default:
        return 'text-secondary';
    }
  }
}
