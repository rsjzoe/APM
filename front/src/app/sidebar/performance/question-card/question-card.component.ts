import { Component, Input } from '@angular/core';
import { Question } from '../../../application-APM/appType';
import { IconDeleteComponent } from '../../../components/icons/icon-delete/icon-delete.component';
import { IconEditComponent } from '../../../components/icons/icon-edit/icon-edit.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-question-card',
  imports: [IconDeleteComponent, IconEditComponent, CommonModule],
  templateUrl: './question-card.component.html',
  styleUrl: './question-card.component.scss',
})
export class QuestionCardComponent {
  @Input() question!: Question;
  @Input() editQuestion = (question: Question) => {};
  @Input() deleteQuestion = (id: number) => {};
}
