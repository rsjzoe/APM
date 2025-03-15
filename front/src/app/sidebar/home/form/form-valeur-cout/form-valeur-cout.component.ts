import { Component } from '@angular/core';
import { ModalStateService } from '../../modal-add-app/modal-state.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-form-valeur-cout',
  imports: [FormsModule, CommonModule],
  templateUrl: './form-valeur-cout.component.html',
  styleUrl: './form-valeur-cout.component.scss',
})
export class FormValeurCoutComponent {
  constructor(public modalStateService: ModalStateService) {}
}
