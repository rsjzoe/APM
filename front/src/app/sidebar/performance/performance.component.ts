import { Component, ViewEncapsulation } from '@angular/core';
import { QuestionCardComponent } from './question-card/question-card.component';
import { ModalQuestionFormComponent } from './modal-question-form/modal-question-form.component';
import { QuestionGroupe } from '../../application/appType';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { QuestionGroupeService } from './service/questionGroupe.service';
import { ButtonComponent } from '../../components/button/button.component';

@Component({
  selector: 'app-performance',
  imports: [
    QuestionCardComponent,
    FormsModule,
    CommonModule,
    ModalQuestionFormComponent,
    ButtonComponent,
  ],
  templateUrl: './performance.component.html',
  styleUrl: './performance.component.scss',
  encapsulation: ViewEncapsulation.None,
})
export class PerformanceComponent {
  questions: QuestionGroupe[] = [];
  isEditingId: number | null = null;
  questionEditing: QuestionGroupe | null = null;

  constructor(private questionGroupeService: QuestionGroupeService) {}

  generateRandomColor(): string {
    const colors = [
      '#ff7900',
      '#4bb4e6',
      '#ff8ad4',
      '#492191',
      '#ffb400',
      '#27a971',
    ];
    return colors[Math.floor(Math.random() * colors.length)];
  }

  addQuestion = (question: string, coeff: number) => {};

  deleteQuestion = (id: number) => {};

  editQuestion = (question: QuestionGroupe) => {
    this.isEditingId = question.id;
    this.questionEditing = question;
  };

  updateQuetion = (questionText: string, coeff: number) => {
    if (this.isEditingId == null) return;
  };

  findAll = () => {};

  ngOnInit() {
    this.findAll();
  }
}
