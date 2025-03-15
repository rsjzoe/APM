import { Component } from '@angular/core';
import { FormValeurCoutComponent } from '../../form/form-valeur-cout/form-valeur-cout.component';
import { FormsModule } from '@angular/forms';
import { ModalStateService } from '../../modal-add-app/modal-state.service';

@Component({
  selector: 'app-accordion-valeur-cout',
  imports: [FormValeurCoutComponent, FormsModule],
  templateUrl: './accordion-valeur-cout.component.html',
  styleUrl: './accordion-valeur-cout.component.scss',
})
export class AccordionValeurCoutComponent {
  constructor(public modalStateService: ModalStateService) {}
}
