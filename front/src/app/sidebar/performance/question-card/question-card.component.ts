import { Component, Input } from '@angular/core';
import { Question, QuestionGroupe } from '../../../application/question.type';
import { IconDeleteComponent } from '../../../components/icons/icon-delete/icon-delete.component';
import { IconEditComponent } from '../../../components/icons/icon-edit/icon-edit.component';
import { CommonModule } from '@angular/common';
import { ButtonComponent } from '../../../components/button/button.component';
import { FormsModule } from '@angular/forms';
import { ModalConfirmComponent } from '../../../components/modal-confirm/modal-confirm.component';
import { QuestionService } from '../service/question.service';

@Component({
  selector: 'app-question-card',
  imports: [
    IconDeleteComponent,
    FormsModule,
    IconEditComponent,
    CommonModule,
    ButtonComponent,
    ModalConfirmComponent,
  ],
  templateUrl: './question-card.component.html',
  styleUrl: './question-card.component.scss',
})
export class QuestionCardComponent {
  @Input() questionGroupe!: QuestionGroupe;
  @Input() editQuestion = (question: QuestionGroupe) => {};
  @Input() deleteQuestion = (id: number) => {};
  @Input() refreshQuestionGroup = () => {};
  questionGroupeIdDelete: number | null = null;
  subQuestionIdDelete: number | null = null;

  showAddQuestion = false;
  newQuestionText = '';
  editingSubQuestion: Question | null = null;

  constructor(private questionService: QuestionService) {}

  saveIdQestionGroupeDelete = (id: number) => {
    this.questionGroupeIdDelete = id;
  };

  onConfirmDelete = () => {
    if (this.questionGroupeIdDelete) {
      this.deleteQuestion(this.questionGroupeIdDelete);
    }
  };

  toggleAddQuestion() {
    this.showAddQuestion = !this.showAddQuestion;
    this.newQuestionText = '';
    this.editingSubQuestion = null;
  }

  addSubQuestion() {
    if (this.newQuestionText.trim()) {
      if (this.editingSubQuestion) {
        // Update existing question
        this.questionService
          .update(this.editingSubQuestion.id, {
            text: this.newQuestionText,
            questionGroupId: this.questionGroupe.id,
          })
          .subscribe({
            next: () => {
              this.refreshQuestionGroup();
            },
          });
        this.editingSubQuestion = null;
      } else {
        // add new question
        this.questionService
          .add({
            text: this.newQuestionText,
            questionGroupId: this.questionGroupe.id,
          })
          .subscribe({
            next: () => {
              this.refreshQuestionGroup();
            },
          });
      }
      this.newQuestionText = '';
      this.showAddQuestion = false;
    }
  }

  cancelAddQuestion() {
    this.showAddQuestion = false;
    this.newQuestionText = '';
    this.editingSubQuestion = null;
  }

  editSubQuestion(subQuestion: Question) {
    this.showAddQuestion = true;
    this.newQuestionText = subQuestion.text;
    this.editingSubQuestion = subQuestion;
  }

  deleteSubQuestion(id: number) {
    this.questionService.delete(id).subscribe({
      next: () => {
        this.refreshQuestionGroup();
      },
    });
  }
}
