import { Component } from '@angular/core';
import { ModalStateService } from '../modal-state.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-modal-document',
  imports: [FormsModule],
  templateUrl: './modal-document.component.html',
  styleUrl: './modal-document.component.scss',
})
export class ModalDocumentComponent {
  constructor(public modalStateService: ModalStateService) {}
}
