import { Component } from '@angular/core';
import { ModalStateService } from '../../modal-add-app/modal-state.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FormDateStatusComponent } from '../../form/form-date-status/form-date-status.component';

@Component({
  selector: 'app-accordion-date-status',
  imports: [FormsModule, CommonModule, FormDateStatusComponent],
  templateUrl: './accordion-date-status.component.html',
  styleUrl: './accordion-date-status.component.scss',
})
export class AccordionDateStatusComponent {
  constructor(public modalStateService: ModalStateService) {}
}
