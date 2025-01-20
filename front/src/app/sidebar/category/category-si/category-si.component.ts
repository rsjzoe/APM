import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Application } from '../../../application-APM/appType';

@Component({
  selector: 'app-category-si',
  imports: [FormsModule, CommonModule],
  templateUrl: './category-si.component.html',
  styleUrl: './category-si.component.scss',
})
export class CategorySiComponent {
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
}
