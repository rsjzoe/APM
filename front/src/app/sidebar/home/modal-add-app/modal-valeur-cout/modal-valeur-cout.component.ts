import { Component, Input, SimpleChanges } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ValueFromCost } from '../modal-type';
import { Application } from '../../../../application-APM/appType';

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
  @Input() appEditing: Application | null = null;

  @Input() getValueFromCost(value: ValueFromCost) {}

  ngOnChanges(changes: SimpleChanges) {
    console.log(changes);
    const currenAppEditing: Application | null =
      changes['appEditing'].currentValue;
    if (currenAppEditing == null) {
      return;
    }
    this.businessValue = currenAppEditing.businessValue;
    this.costBuild = currenAppEditing.costBuild;
    this.costRun = currenAppEditing.costRun;
    this.userTotal = currenAppEditing.userTotal;
  }
}
