import { Component } from '@angular/core';
import { CardComponent } from "./card/card.component";
import { applications } from '../../application-APM/data';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [CardComponent, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  apps = applications
}
