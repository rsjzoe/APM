import { Component } from '@angular/core';
import { Application } from '../../../application-APM/appType';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-category-si',
  imports: [FormsModule, CommonModule],
  templateUrl: './category-si.component.html',
  styleUrl: './category-si.component.scss',
})
export class CategorySiComponent {
  application: Application = {
    id: 5,
    name: 'Internal Messaging System',
    description: 'Application de messagerie interne utilisée par les employés.',
    businessValue: 20000,
    costBuild: 15000,
    costRun: 8000,
    userTeam: 'Tous les départements',
    category: 'SI',
    startDate: new Date('2015-04-10'),
    lastUpdate: new Date('2022-06-30'),
    performance: {
      responseTimeMs: 12,
    },
    status: 'production',
    time: 'migrate',
    userTotal: 18,
  };

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
