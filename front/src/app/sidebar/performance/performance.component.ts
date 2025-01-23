import { Component } from '@angular/core';
import { QuestionCardComponent } from './question-card/question-card.component';
import { ModalQuestionFormComponent } from './modal-question-form/modal-question-form.component';
import { Question } from '../../application-APM/appType';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-performance',
  imports: [QuestionCardComponent, FormsModule,CommonModule, ModalQuestionFormComponent],
  templateUrl: './performance.component.html',
  styleUrl: './performance.component.scss',
})
export class PerformanceComponent {
  questions: Question[] = [];
  isEditingId: number | null = null;
  questionEditing: Question | null = null;

  addQuestion = (question: string) => {
    this.questions.push({
      id: Date.now(),
      text: question,
    });
  };

  deleteQuestion = (id: number) => {
    this.questions = this.questions.filter((question) => question.id !== id);
  };

  editQuestion = (question: Question) => {
    this.isEditingId = question.id;
    this.questionEditing = question;
  };

  updateQuetion = (questionText: string) => {
    if (this.isEditingId == null) return;
    const updateQuestion: Question = {
      id: this.isEditingId,
      text: questionText,
    };
    for (let question of this.questions) {
      if (question.id == updateQuestion.id) {
        question.text = updateQuestion.text;
      }
    }
  };
}
