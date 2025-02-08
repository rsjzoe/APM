import { Component, Input, SimpleChanges } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Application, Status } from '../../../../application-APM/appType';
import { ValueFromStatus } from '../modal-type';

@Component({
  selector: 'app-modal-status-date',
  imports: [FormsModule],
  templateUrl: './modal-status-date.component.html',
  styleUrl: './modal-status-date.component.scss',
})
export class ModalStatusDateComponent {
  startDate!: Date;
  lastUpdate!: Date;
  status!: Status;
  @Input() appEditing: Application | null = null;

  @Input() getValueFromStatus(value: ValueFromStatus) {}

  ngOnChanges(changes: SimpleChanges) {
    const currenAppEditing: Application | null =
      changes['appEditing'].currentValue;
    if (currenAppEditing == null) {
      return;
    }
    this.startDate = currenAppEditing.startDate;
    this.lastUpdate = currenAppEditing.lastUpdate;
    this.status = currenAppEditing.status;
  }
}
