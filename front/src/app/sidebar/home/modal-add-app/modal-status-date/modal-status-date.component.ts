import { Component, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Status } from '../../../../application-APM/appType';
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

  @Input() getValueFromStatus(value: ValueFromStatus) {}
}
