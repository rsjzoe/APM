import { Component, OnInit } from '@angular/core';
import { Application } from '../../../application-APM/appType';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { IconLifecycleComponent } from '../../../components/icons/icon-lifecycle/icon-lifecycle.component';
import { IconStarComponent } from '../../../components/icons/icon-star/icon-star.component';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { HomeService } from '../home.service';

@Component({
  selector: 'app-app-details',
  imports: [
    FormsModule,
    CommonModule,
    IconLifecycleComponent,
    IconStarComponent,
    RouterLink,
  ],
  templateUrl: './app-details.component.html',
  styleUrl: './app-details.component.scss',
})
export class AppDetailsComponent implements OnInit {
  application: Application | null = null;
  appId: number | null = null;

  constructor(
    private appService: HomeService,
    private route: ActivatedRoute,
    private router: Router
  ) {}
  ngOnInit() {
    this.appId = Number(this.route.snapshot.paramMap.get('id'));
    this.findById(this.appId);
  }

  findById(id: number) {
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

  getStatusColor(status: Application['status']): string {
    switch (status) {
      case 'development':
        return 'bg-warning';
      case 'production':
        return 'bg-success';
      case 'deprecated':
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
