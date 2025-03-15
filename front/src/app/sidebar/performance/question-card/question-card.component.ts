import { Component, Input } from '@angular/core';
import { QuestionGroupe } from '../../../application/appType';
import { IconDeleteComponent } from '../../../components/icons/icon-delete/icon-delete.component';
import { IconEditComponent } from '../../../components/icons/icon-edit/icon-edit.component';
import { CommonModule } from '@angular/common';
import { ButtonComponent } from '../../../components/button/button.component';
import { FormsModule } from '@angular/forms';
import { ModalConfirmComponent } from '../../../components/modal-confirm/modal-confirm.component';

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
  @Input() question!: QuestionGroupe;
  @Input() editQuestion = (question: QuestionGroupe) => {};
  @Input() deleteQuestion = (id: number) => {};
  questionGroupeIdDelete: number | null = null;
  subQuestionIdDelete: number | null = null;

  subQuestions = [
    { id: 1, text: 'How do you ensure performance in your application?' },
    { id: 2, text: 'What strategies do you use for scalability?' },
    { id: 3, text: 'How do you handle high traffic loads?' },
  ];

  showAddQuestion = false;
  newQuestionText = '';
  editingSubQuestion: any = null;

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
        const index = this.subQuestions.findIndex(
          (q) => q.id === this.editingSubQuestion.id
        );
        if (index !== -1) {
          this.subQuestions[index].text = this.newQuestionText;
        }
        this.editingSubQuestion = null;
      } else {
        // Add new question
        const newId = Math.max(0, ...this.subQuestions.map((q) => q.id)) + 1;
        this.subQuestions.push({
          id: newId,
          text: this.newQuestionText,
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

  editSubQuestion(subQuestion: any) {
    this.showAddQuestion = true;
    this.newQuestionText = subQuestion.text;
    this.editingSubQuestion = subQuestion;
  }

  deleteSubQuestion(id: number) {
    this.subQuestions = this.subQuestions.filter((q) => q.id !== id);
  }
}
