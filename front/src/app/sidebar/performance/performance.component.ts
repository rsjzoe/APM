import { Component, ViewEncapsulation } from '@angular/core';
import { QuestionCardComponent } from './question-card/question-card.component';
import { ModalQuestionFormComponent } from './modal-question-form/modal-question-form.component';
import {
  QuestionGroupe,
  QuestionGroupeType,
} from '../../application/question.type';
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

  addQuestion = (question: string, coeff: number, type: QuestionGroupeType) => {
    this.questionGroupeService
      .add({
        text: question,
        coeff,
        type,
        borderColor: this.generateRandomColor(),
      })
      .subscribe({
        next: () => {
          this.findAll();
        },
      });
  };

  deleteQuestion = (id: number) => {
    this.questionGroupeService.delete(id).subscribe({
      next: () => {
        this.findAll();
      },
    });
  };

  editQuestion = (question: QuestionGroupe) => {
    this.isEditingId = question.id;
    this.questionEditing = question;
  };

  updateQuetion = (
    questionText: string,
    coeff: number,
    type: QuestionGroupeType
  ) => {
    if (this.isEditingId == null || this.questionEditing == null) return;
    this.questionGroupeService
      .update(this.isEditingId, {
        text: questionText,
        coeff,
        type,
        borderColor: this.questionEditing.borderColor,
      })
      .subscribe({
        next: () => {
          this.isEditingId = null;
          this.questionEditing = null;
          this.findAll();
        },
      });
  };

  findAll = () => {
    this.questionGroupeService.findAll().subscribe({
      next: (questions) => {
        this.questions = questions;
      },
    });
  };

  ngOnInit() {
    this.findAll();
  }
}
