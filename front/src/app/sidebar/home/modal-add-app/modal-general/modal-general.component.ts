import { Component, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ValueFromGeneral } from '../modal-type';
import { Category } from '../../../../application-APM/appType';

@Component({
  selector: 'app-modal-general',
  imports: [FormsModule],
  templateUrl: './modal-general.component.html',
  styleUrl: './modal-general.component.scss',
})
export class ModalGeneralComponent {
  appName: string = '';
  description = '';
  category = '' as Category;
  userTeam = '';
  @Input() getValueFromGeneral(value: ValueFromGeneral) {}
}
