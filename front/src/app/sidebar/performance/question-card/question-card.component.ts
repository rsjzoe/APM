import { Component, Input } from '@angular/core';
import { Question } from '../../../application-APM/appType';

@Component({
  selector: 'app-question-card',
  imports: [],
  templateUrl: './question-card.component.html',
  styleUrl: './question-card.component.scss',
})
export class QuestionCardComponent {
  @Input() question!: Question;
  @Input() editQuestion = (question: Question) => {};
  @Input() deleteQuestion = (id: number) => {};
}
