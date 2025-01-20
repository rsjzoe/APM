import { Component, Input } from '@angular/core';
import { Application } from '../../../application-APM/appType';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-card',
  imports: [RouterLink],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss',
})
export class CardComponent {
  @Input() app!: Application;
  @Input() deleteById(id: number) {}
  @Input() route: string = '';
}
