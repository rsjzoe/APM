import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ModalStateService } from '../modal-state.service';
import { FormGeneralComponent } from '../../form/form-general/form-general.component';

@Component({
  selector: 'app-modal-general',
  imports: [FormsModule, CommonModule, FormGeneralComponent],
  templateUrl: './modal-general.component.html',
  styleUrl: './modal-general.component.scss',
})
export class ModalGeneralComponent {
  constructor(public modalStateService: ModalStateService) {}
}
