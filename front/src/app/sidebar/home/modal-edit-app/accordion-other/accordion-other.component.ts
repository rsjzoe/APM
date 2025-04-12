import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ModalStateService } from '../../modal-add-app/modal-state.service';

@Component({
  selector: 'app-accordion-other',
  imports: [FormsModule],
  templateUrl: './accordion-other.component.html',
  styleUrl: './accordion-other.component.scss',
})
export class AccordionOtherComponent {
  constructor(public modalStateService: ModalStateService) {}
}
