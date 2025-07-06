import { Component } from '@angular/core';
import { ModalStateService } from '../modal-add-app/modal-state.service';
import { AccordionGeneralComponent } from './accordion-general/accordion-general.component';
import { AccordionValeurCoutComponent } from './accordion-valeur-cout/accordion-valeur-cout.component';
import { AccordionDateStatusComponent } from './accordion-date-status/accordion-date-status.component';
import { AccordionOtherComponent } from "./accordion-other/accordion-other.component";
import { ModalComponent } from "../../../components/modal/modal.component";

@Component({
  selector: 'app-modal-edit-app',
  imports: [
    AccordionGeneralComponent,
    AccordionValeurCoutComponent,
    AccordionDateStatusComponent,
    AccordionOtherComponent,
    ModalComponent
],
  templateUrl: './modal-edit-app.component.html',
  styleUrl: './modal-edit-app.component.scss',
})
export class ModalEditAppComponent {
  constructor(public modalStateService: ModalStateService) {}
}
