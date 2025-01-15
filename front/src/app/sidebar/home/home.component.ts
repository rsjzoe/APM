import { Component } from '@angular/core';
import { CardComponent } from "./card/card.component";
import { applications } from '../../application-APM/data';
import { CommonModule } from '@angular/common';
import { ButtonComponent } from "../../components/button/button.component";
import { ModalAddAppComponent } from "./modal-add-app/modal-add-app.component";

@Component({
  selector: 'app-home',
  imports: [CardComponent, CommonModule, ButtonComponent, ModalAddAppComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  apps = applications
}
