import { Component } from '@angular/core';
import { QuestionCardComponent } from './question-card/question-card.component';
import { ModalQuestionFormComponent } from './modal-question-form/modal-question-form.component';
import { Question } from '../../application-APM/appType';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { QuestionService } from './question.service';
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
})
export class PerformanceComponent {
  questions: Question[] = [];
  isEditingId: number | null = null;
  questionEditing: Question | null = null;

  constructor(private questionService: QuestionService) {}

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

  addQuestion = (question: string) => {
    this.questionService
      .add({ text: question, borderColor: this.generateRandomColor() })
      .subscribe({
        next: (newQuestion) => {
          this.questions.push(newQuestion);
        },
        error: (error) => {
          console.log("erreur de l'ajout : " + error);
        },
      });
  };

  deleteQuestion = (id: number) => {
    this.questionService.delete(id).subscribe({
      next: () => {
        this.questions = this.questions.filter(
          (question) => question.id !== id
        );
      },
      error: (error) => {
        console.log('erreur de la suppresssion : ' + error);
      },
    });
  };

  editQuestion = (question: Question) => {
    this.isEditingId = question.id;
    this.questionEditing = question;
  };

  updateQuetion = (questionText: string) => {
    if (this.isEditingId == null) return;
    this.questionService
      .update(this.isEditingId, {
        text: questionText,
        borderColor: this.generateRandomColor(),
      })
      .subscribe({
        next: (val) => {
          for (let question of this.questions) {
            if (question.id == val.id) {
              question.text = val.text;
            }
          }
          this.isEditingId = null;
          this.questionEditing = null;
        },
        error: (error) => {
          console.log('erreur de la modification : ' + error);
        },
      });
  };

  findAll = () => {
    
    this.questionService.findAll().subscribe({
      next: (data) => {
        this.questions = data;
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des questions :', error);
      },
    });

  };

  ngOnInit() {
    this.findAll();
  }
}
