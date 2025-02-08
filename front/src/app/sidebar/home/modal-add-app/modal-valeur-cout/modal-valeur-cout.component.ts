import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ModalStateService } from '../modal-state.service';

@Component({
  selector: 'app-modal-valeur-cout',
  imports: [FormsModule],
  templateUrl: './modal-valeur-cout.component.html',
  styleUrl: './modal-valeur-cout.component.scss',
})
export class ModalValeurCoutComponent {
  constructor(public modalStateService: ModalStateService) {}
}
