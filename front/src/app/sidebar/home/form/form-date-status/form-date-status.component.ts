import { Component } from '@angular/core';
import { ModalStateService } from '../../modal-add-app/modal-state.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-form-date-status',
  imports: [FormsModule, CommonModule],
  templateUrl: './form-date-status.component.html',
  styleUrl: './form-date-status.component.scss',
})
export class FormDateStatusComponent {
  constructor(public modalStateService: ModalStateService) {}
}
