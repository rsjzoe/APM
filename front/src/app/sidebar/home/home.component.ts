import { Component } from '@angular/core';
import { CardComponent } from "./card/card.component";
import { applications } from '../../application-APM/data';
import { CommonModule } from '@angular/common';
import { ButtonComponent } from "../../components/button/button.component";

@Component({
  selector: 'app-home',
  imports: [CardComponent, CommonModule, ButtonComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  apps = applications
}
