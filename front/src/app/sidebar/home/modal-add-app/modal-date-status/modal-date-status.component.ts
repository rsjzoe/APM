import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ModalStateService } from '../modal-state.service';
import { FormDateStatusComponent } from "../../form/form-date-status/form-date-status.component";

@Component({
  selector: 'app-modal-date-status',
  imports: [FormsModule, FormDateStatusComponent],
  templateUrl: './modal-date-status.component.html',
  styleUrl: './modal-date-status.component.scss',
})
export class ModalDateStatusComponent {
  constructor(public modalStateService: ModalStateService) {}
}
