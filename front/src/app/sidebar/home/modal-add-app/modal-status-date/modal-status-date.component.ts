import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ModalStateService } from '../modal-state.service';

@Component({
  selector: 'app-modal-status-date',
  imports: [FormsModule],
  templateUrl: './modal-status-date.component.html',
  styleUrl: './modal-status-date.component.scss',
})
export class ModalStatusDateComponent {
  constructor(public modalStateService: ModalStateService) {}
}
