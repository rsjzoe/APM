import { Component } from '@angular/core';
import { QuestionCardComponent } from "./question-card/question-card.component";
import { ModalQuestionFormComponent } from "./modal-question-form/modal-question-form.component";

@Component({
  selector: 'app-performance',
  imports: [QuestionCardComponent, ModalQuestionFormComponent],
  templateUrl: './performance.component.html',
  styleUrl: './performance.component.scss'
})
export class PerformanceComponent {

}
