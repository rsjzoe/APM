import { Component, ViewEncapsulation } from '@angular/core';
import {
  Question,
  QuestionGroupe,
  QuestionGroupeType,
} from '../../application/question/question.type';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { QuestionGroupeService } from './service/questionGroupe.service';
import { QuestionService } from './service/question.service';
import { IconEditComponent } from '../../components/icons/icon-edit/icon-edit.component';
import { IconDeleteComponent } from '../../components/icons/icon-delete/icon-delete.component';
import { IconPlusComponent } from '../../components/icons/icon-plus/icon-plus.component';
// import Tooltip from './boosted/js/dist/tooltip';
import { IconHelpComponent } from '../../components/icons/icon-help/icon-help.component';
import { Observable } from 'rxjs';
import { UserService } from '../administration/user.service';
import { ToastService } from '../../components/toast/service/toast.service';
import { SocketService } from '../../socket.service';

@Component({
  selector: 'app-questionnaire',
  imports: [
    FormsModule,
    CommonModule,
    IconEditComponent,
    IconDeleteComponent,
    IconPlusComponent,
    IconHelpComponent,
  ],
  templateUrl: './questionnaire.component.html',
  styleUrl: './questionnaire.component.scss',
  encapsulation: ViewEncapsulation.None,
})
export class QuestionnaireComponent {
  QuestionGroupeType = QuestionGroupeType;
  questionGroups: QuestionGroupe[] = [];
  selectedGroup: QuestionGroupe | null = null;
  editingGroup: QuestionGroupe | null = null;
  editingQuestion: Question | null = null;
  canAdd$!: Observable<boolean>;
  canEdit$!: Observable<boolean>;
  canDelete$!: Observable<boolean>;
  groupedByType: GroupedByType[] = [];
  openedGroupIndex: number | null = null;

  constructor(
    private questionGroupeService: QuestionGroupeService,
    private questionService: QuestionService,
    private userService: UserService,
    private toastService: ToastService,
    private socketService: SocketService
  ) {
    this.socketService.onEvent('refetch_question', () => {
      this.init();
    });
  }

  toggleAccordion(index: number) {
    this.openedGroupIndex = this.openedGroupIndex === index ? null : index;
  }

  getQuestionsGroup(): GroupedByType[] {
    return [
      {
        name: 'Valeur métier',
        data: this.questionGroups.filter(
          (group) => group.type === QuestionGroupeType.businessValue
        ),
      },
      {
        name: 'Dette technique',
        data: this.questionGroups.filter(
          (group) => group.type === QuestionGroupeType.technicalDebt
        ),
      },
    ];
  }

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
          this.toastService.success('Groupe de questions ajoutée avec succès');
        },
        error: (err) => {
          this.toastService.error(
            "Erreur lors de l'ajout du groupe de questions"
          );
          console.error(err);
        },
      });
  };

  deleteQuestionGroupe = (id: number) => {
    this.questionGroupeService.delete(id).subscribe({
      next: () => {
        this.findAll();
        this.toastService.success('Groupe de questions supprimée avec succès');
      },
      error: (err) => {
        this.toastService.error(
          'Erreur lors de la suppression du groupe de questions"'
        );
        console.error(err);
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
          this.toastService.success('Groupe de questions modifiée avec succès');
        },
        error: (err) => {
          this.toastService.error(
            'Erreur lors de la modification du groupe de questions"'
          );
          console.error(err);
        },
      });
  };

  findAll = () => {
    this.questionGroupeService.findAll().subscribe({
      next: (questions) => {
        this.questionGroups = questions;
        this.groupedByType = this.getQuestionsGroup();
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
          this.toastService.success('Question ajoutée avec succès');
        },
        error: (err) => {
          this.toastService.error("Erreur lors de l'ajout de la question");
          console.error(err);
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
        next: () => {
          this.findAll();
          const questions = this.selectedGroup?.questions;
          if (questions) {
            for (const question of questions) {
              if (question.id == questionId) {
                question.text = text;
              }
            }
          }
          this.toastService.success('Question modifiée avec succès');
        },
        error: (err) => {
          this.toastService.error(
            'Erreur lors de la modification de la question'
          );
          console.error(err);
        },
      });
  };

  deleteQuestion = (id: number) => {
    this.questionService.delete(id).subscribe({
      next: () => {
        this.findAll();
        if (this.selectedGroup) {
          this.selectedGroup.questions = this.selectedGroup.questions.filter(
            (q) => q.id != id
          );
        }
        this.toastService.success('Question supprimée avec succès');
      },
      error: (err) => {
        this.toastService.error(
          'Erreur lors de la suppression de la catégorie Question'
        );
        console.error(err);
      },
    });
  };

  init() {
    this.canAdd$ = this.userService.canCreateService('performance');
    this.canEdit$ = this.userService.canEditService('performance');
    this.canDelete$ = this.userService.canDeleteService('performance');
    this.findAll();
  }

  ngOnInit() {
    this.init();
  }
}

type GroupedByType = {
  name: 'Valeur métier' | 'Dette technique';
  data: QuestionGroupe[];
};
