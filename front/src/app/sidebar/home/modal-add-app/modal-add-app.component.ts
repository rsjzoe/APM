import { Component, ViewEncapsulation } from '@angular/core';
import { ModalGeneralComponent } from './modal-general/modal-general.component';
import { ModalStatusDateComponent } from './modal-status-date/modal-status-date.component';
import { ModalTechPerformComponent } from './modal-tech-perform/modal-tech-perform.component';
import { ModalValeurCoutComponent } from './modal-valeur-cout/modal-valeur-cout.component';

@Component({
  selector: 'app-modal-add-app',
  imports: [
    ModalGeneralComponent,
    ModalStatusDateComponent,
    ModalTechPerformComponent,
    ModalValeurCoutComponent,
  ],
  templateUrl: './modal-add-app.component.html',
  styleUrl: './modal-add-app.component.scss',
  encapsulation: ViewEncapsulation.None,
})
export class ModalAddAppComponent {}
