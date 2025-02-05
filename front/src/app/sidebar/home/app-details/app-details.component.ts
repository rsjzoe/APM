import { Component, OnInit } from '@angular/core';
import { Application } from '../../../application-APM/appType';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { IconLifecycleComponent } from '../../../components/icons/icon-lifecycle/icon-lifecycle.component';
import { IconStarComponent } from '../../../components/icons/icon-star/icon-star.component';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ApplicationService } from '../home.service';
import { TimelineComponent } from "../../../components/timeline/timeline.component";
import { TimelineHistoryComponent } from "./timeline-history/timeline-history.component";

@Component({
  selector: 'app-app-details',
  imports: [
    FormsModule,
    CommonModule,
    IconLifecycleComponent,
    IconStarComponent,
    RouterLink,
    TimelineComponent,
    TimelineHistoryComponent
],
  templateUrl: './app-details.component.html',
  styleUrl: './app-details.component.scss',
})
export class AppDetailsComponent implements OnInit {
  application: Application | null = null;
  appId: number | null = null;

  constructor(
    private appService: ApplicationService,
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

  activeTab: string = 'dashboard';

  historyData = [
    {
      date: "2023-06-01",
      type: "update",
      description: "Mise à jour des fonctionnalités de reporting",
      author: "Sophie Martin",
    },
    {
      date: "2023-05-15",
      type: "cost",
      description: "Augmentation du coût d'exploitation à 5000€",
      author: "Lucas Dubois",
    },
    {
      date: "2023-04-22",
      type: "feature",
      description: "Ajout d'une nouvelle fonctionnalité de chat en temps réel",
      author: "Emma Lefebvre",
    },
    {
      date: "2023-03-10",
      type: "delete",
      description: "Suppression du module de gestion des stocks obsolète",
      author: "Thomas Petit",
    },
    {
      date: "2023-02-05",
      type: "update",
      description: "Optimisation des performances du tableau de bord",
      author: "Camille Roux",
    },
  ];

  setActiveTab(tab: string) {
    this.activeTab = tab;
  }

  formatDate(date: string) {
    return new Date(date).toLocaleDateString("fr-FR", {
      year: "numeric",
      month: "long",
      day: "numeric",
    });
  }
}
