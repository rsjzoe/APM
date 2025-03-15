import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ModalStateService } from '../modal-state.service';
import { FormValeurCoutComponent } from "../../form/form-valeur-cout/form-valeur-cout.component";

@Component({
  selector: 'app-modal-valeur-cout',
  imports: [FormsModule, FormValeurCoutComponent],
  templateUrl: './modal-valeur-cout.component.html',
  styleUrl: './modal-valeur-cout.component.scss',
})
export class ModalValeurCoutComponent {
  constructor(public modalStateService: ModalStateService) {}
}
