import { Component, ViewEncapsulation } from '@angular/core';
import { ModalDocumentComponent } from './modal-document/modal-document.component';
import { ModalGeneralComponent } from './modal-general/modal-general.component';
import { ModalValeurCoutComponent } from './modal-valeur-cout/modal-valeur-cout.component';
import { ModalDateStatusComponent } from './modal-date-status/modal-date-status.component';

@Component({
  selector: 'app-modal-add-app',
  imports: [
    ModalDateStatusComponent,
    ModalValeurCoutComponent,
    ModalGeneralComponent,
    ModalDocumentComponent,
  ],
  templateUrl: './modal-add-app.component.html',
  styleUrl: './modal-add-app.component.scss',
  encapsulation: ViewEncapsulation.None,
})
export class ModalAddAppComponent {}
