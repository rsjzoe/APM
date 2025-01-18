import { Component, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ValueFromCost } from '../modal-type';

@Component({
  selector: 'app-modal-valeur-cout',
  imports: [FormsModule],
  templateUrl: './modal-valeur-cout.component.html',
  styleUrl: './modal-valeur-cout.component.scss',
})
export class ModalValeurCoutComponent {
  businessValue!: number;
  costBuild!: number;
  costRun!: number;
  userTotal!: number;

  @Input() getValueFromCost(value: ValueFromCost) {}
}
