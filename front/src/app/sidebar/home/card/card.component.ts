import { Component, Input } from '@angular/core';
import { Application } from '../../../application-APM/appType';

@Component({
  selector: 'app-card',
  imports: [],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss'
})
export class CardComponent {
  @Input() app!: Application;
}
