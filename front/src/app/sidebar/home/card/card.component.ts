import { Component, Input } from '@angular/core';
import { Application } from '../../../application-APM/appType';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { IconLifecycleComponent } from "../../../components/icons/icon-lifecycle/icon-lifecycle.component";
import { timeColor } from '../../life-cycle-time/color';
import { IconStarComponent } from "../../../components/icons/icon-star/icon-star.component";

@Component({
  selector: 'app-card',
  imports: [RouterLink, CommonModule, IconLifecycleComponent, IconStarComponent],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss',
})
export class CardComponent {
  @Input() app!: Application;
  @Input() deleteById(id: number) {}
  @Input() editById(id: number) {}
  @Input() route: string = '';

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
    return timeColor[time];
  }
}
