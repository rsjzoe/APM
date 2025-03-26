import { Component, ViewEncapsulation } from '@angular/core';
import {
  Question,
  QuestionGroupe,
  QuestionGroupeType,
} from '../../application/question.type';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { QuestionGroupeService } from './service/questionGroupe.service';
import { QuestionService } from './service/question.service';
import { IconEditComponent } from "../../components/icons/icon-edit/icon-edit.component";
import { IconDeleteComponent } from "../../components/icons/icon-delete/icon-delete.component";
import { IconPlusComponent } from "../../components/icons/icon-plus/icon-plus.component";

@Component({
  selector: 'app-performance',
  imports: [FormsModule, CommonModule, IconEditComponent, IconDeleteComponent, IconPlusComponent],
  templateUrl: './performance.component.html',
  styleUrl: './performance.component.scss',
  encapsulation: ViewEncapsulation.None,
})
export class PerformanceComponent {
  QuestionGroupeType = QuestionGroupeType;
  questionGroups: QuestionGroupe[] = [];
  selectedGroup: QuestionGroupe | null = null;
  editingGroup: QuestionGroupe | null = null;
  editingQuestion: Question | null = null;

  constructor(
    private questionGroupeService: QuestionGroupeService,
    private questionService: QuestionService
  ) {}

  handleSelectGroup(group: QuestionGroupe): void {
    this.selectedGroup = group;
    this.editingGroup = null;
    this.editingQuestion = null;
  }

  handleEditGroup(group: QuestionGroupe): void {
    this.editingGroup = { ...group };
    this.editingQuestion = null;
  }

  handleSaveGroup(): void {
    if (this.editingGroup) {
      if (this.editingGroup.id === 0) {
        // Update group
        this.addQuestionGroupe(
          this.editingGroup.text,
          this.editingGroup.coeff,
          this.editingGroup.type
        );
      } else {
        // Update existing group
        this.questionGroups = this.questionGroups.map((g) =>
          g.id === this.editingGroup!.id ? this.editingGroup! : g
        );
        this.updateQuetionGroupe(
          this.editingGroup.text,
          this.editingGroup.coeff,
          this.editingGroup.type
        );
        this.selectedGroup = this.editingGroup;
      }
      this.editingGroup = null;
    }
  }

  handleCancelEditGroup(): void {
    this.editingGroup = null;
  }

  handleDeleteGroup(id: number): void {
    this.deleteQuestionGroupe(id);
  }

  handleAddNewGroup(): void {
    this.editingGroup = {
      id: 0,
      text: '',
      borderColor: '#CCCCCC',
      coeff: 1,
      questions: [],
      type: QuestionGroupeType.businessValue,
    };
    this.selectedGroup = null;
  }

  handleEditQuestion(question: Question): void {
    this.editingQuestion = { ...question };
  }

  updateEditingQuestionText(text: string): void {
    if (this.editingQuestion) {
      this.editingQuestion = { ...this.editingQuestion, text };
    }
  }

  handleSaveQuestion(): void {
    if (this.editingQuestion && this.selectedGroup) {
      const updatedGroup = { ...this.selectedGroup };

      if (this.editingQuestion.id === 0) {
        // Add new question
        this.addQuestion(this.editingQuestion.text, this.selectedGroup.id);
      } else {
        // Update existing question
        this.updateQuestion(
          this.editingQuestion.id,
          this.editingQuestion.text,
          this.selectedGroup.id
        );
      }
      this.selectedGroup = updatedGroup;
      this.editingQuestion = null;
    }
  }

  handleCancelEditQuestion(): void {
    this.editingQuestion = null;
  }

  handleDeleteQuestion(id: number): void {
    this.deleteQuestion(id);
  }

  handleAddNewQuestion(): void {
    this.editingQuestion = { id: 0, text: '' };
  }

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

  addQuestionGroupe = (
    question: string,
    coeff: number,
    type: QuestionGroupeType
  ) => {
    this.questionGroupeService
      .add({
        text: question,
        coeff,
        type,
        borderColor: this.generateRandomColor(),
      })
      .subscribe({
        next: (newGroup) => {
          this.findAll();
          this.selectedGroup = newGroup;
        },
      });
  };

  deleteQuestionGroupe = (id: number) => {
    this.questionGroupeService.delete(id).subscribe({
      next: () => {
        this.findAll();
      },
    });
  };

  updateQuetionGroupe = (
    questionText: string,
    coeff: number,
    type: QuestionGroupeType
  ) => {
    if (!this.editingGroup) return;
    this.questionGroupeService
      .update(this.editingGroup.id, {
        text: questionText,
        coeff,
        type,
        borderColor: this.editingGroup.borderColor,
      })
      .subscribe({
        next: () => {
          this.findAll();
        },
      });
  };

  findAll = () => {
    this.questionGroupeService.findAll().subscribe({
      next: (questions) => {
        this.questionGroups = questions;
      },
    });
  };

  addQuestion = (text: string, questionGroupId: number) => {
    this.questionService
      .add({
        text,
        questionGroupId,
      })
      .subscribe({
        next: (newQuestion) => {
          this.findAll();
          this.selectedGroup?.questions.push(newQuestion);
        },
      });
  };

  updateQuestion = (
    questionId: number,
    text: string,
    questionGroupId: number
  ) => {
    this.questionService
      .update(questionId, {
        text,
        questionGroupId: questionGroupId,
      })
      .subscribe({
        next: (updatedQuestion) => {
          this.findAll();
          const questions = this.selectedGroup?.questions;
          if (questions) {
            for (const question of questions) {
              if (question.id == questionId) {
                question.text = text;
              }
            }
          }
        },
      });
  };

  deleteQuestion = (id: number) => {
    this.questionService.delete(id).subscribe({
      next: () => {
        this.findAll();
      },
    });
  };

  ngOnInit() {
    this.findAll();
  }
}
