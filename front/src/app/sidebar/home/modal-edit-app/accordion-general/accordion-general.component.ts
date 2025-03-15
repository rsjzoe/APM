import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ModalStateService } from '../../modal-add-app/modal-state.service';
import { FormGeneralComponent } from '../../form/form-general/form-general.component';

@Component({
  selector: 'app-accordion-general',
  imports: [FormsModule, CommonModule, FormGeneralComponent],
  templateUrl: './accordion-general.component.html',
  styleUrl: './accordion-general.component.scss',
})
export class AccordionGeneralComponent {
  constructor(public modalStateService: ModalStateService) {}
}
