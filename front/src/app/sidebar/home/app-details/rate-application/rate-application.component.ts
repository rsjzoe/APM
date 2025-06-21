import { Component, Input } from '@angular/core';
import { StarRatingComponent } from './star-rating/star-rating.component';
import { CommonModule } from '@angular/common';
import { QuestionGroupeService } from '../../../questionnaire/service/questionGroupe.service';
import {
  Application,
  UpdateApplication,
} from '../../../../application/app.type';
import { ApplicationService } from '../../application.service';
import {
  QuestionGroupe,
  QuestionGroupeType,
} from '../../../../application/question/question.type';
import { Router } from '@angular/router';
import { ToastService } from '../../../../components/toast/service/toast.service';

@Component({
  selector: 'app-rate-application',
  imports: [StarRatingComponent, CommonModule],
  templateUrl: './rate-application.component.html',
  styleUrl: './rate-application.component.scss',
})
export class RateApplicationComponent {
  questionGroups: QuestionGroupe[] = [];
  questionGroupsTechnicalDebt: QuestionGroupe[] = [];
  questionGroupsBusinessValue: QuestionGroupe[] = [];

  @Input() application!: Application;
  constructor(
    private questionGroupeService: QuestionGroupeService,
    private appService: ApplicationService,
    private toastService: ToastService
  ) {}

  expandedGroups: boolean[] = [true];
  expandedGroupsVm: boolean[] = [true];

  // Store ratings in a map: {groupId: {questionId: rating}}
  ratings: Map<number, Map<number, number>> = new Map();

  isGroupExpanded(index: number): boolean {
    return this.expandedGroups[index] || false;
  }

  isGroupExpandedVm(index: number): boolean {
    return this.expandedGroupsVm[index] || false;
  }

  toggleGroup(index: number) {
    this.expandedGroups[index] = !this.expandedGroups[index];
  }
  toggleGroupVm(index: number) {
    this.expandedGroupsVm[index] = !this.expandedGroupsVm[index];
  }

  getRatingForQuestion(groupId: number, questionId: number): number {
    if (!this.ratings.has(groupId)) {
      return 0;
    }
    const groupRatings = this.ratings.get(groupId);
    if (!groupRatings || !groupRatings.has(questionId)) {
      return 0;
    }
    return groupRatings.get(questionId) || 0;
  }

  handleRating = (rating: number, groupId: number, questionId: number) => {
    if (!this.ratings.has(groupId)) {
      this.ratings.set(groupId, new Map());
    }
    const groupRatings = this.ratings.get(groupId);
    if (groupRatings) {
      groupRatings.set(questionId, rating);
    }
  };

  canSubmit(): boolean {
    // Check if all questions in all groups have ratings
    return this.questionGroups.every((group) => {
      if (!this.ratings.has(group.id)) {
        return false;
      }
      const groupRatings = this.ratings.get(group.id);
      return group.questions.every(
        (question) =>
          groupRatings?.has(question.id) &&
          (groupRatings.get(question.id) || 0) > 0
      );
    });
  }

  handleSubmit() {
    let updateApplication: UpdateApplication = {
      noteBusinessValue: 0,
      noteTechnicalDebt: 0,
    };
    return this.updateApplication(this.application.id, updateApplication);
  }

  calculateWeightedAverage() {
    let totalTechnicalDebtSum = 0;
    let totalTechnicalDebtCoeff = 0;
    let totalBusinessValueSum = 0;
    let totalBusinessValueCoeff = 0;

    this.questionGroups.forEach((group) => {
      const groupRatings = this.ratings.get(group.id);
      if (groupRatings) {
        let groupSum = 0;
        group.questions.forEach((question) => {
          groupSum += groupRatings.get(question.id) || 0;
        });

        // Calculate average for this group
        const groupAverage = groupSum / group.questions.length;

        if (group.type === QuestionGroupeType.technicalDebt) {
          totalTechnicalDebtSum += groupAverage * group.coeff;
          totalTechnicalDebtCoeff += group.coeff;
        } else if (group.type === QuestionGroupeType.businessValue) {
          totalBusinessValueSum += groupAverage * group.coeff;
          totalBusinessValueCoeff += group.coeff;
        }
      }
    });

    const technicalDebtAverage =
      totalTechnicalDebtCoeff > 0
        ? totalTechnicalDebtSum / totalTechnicalDebtCoeff
        : 0;
    const businessValueAverage =
      totalBusinessValueCoeff > 0
        ? totalBusinessValueSum / totalBusinessValueCoeff
        : 0;

    return { technicalDebtAverage, businessValueAverage };
  }

  findAllQuestionGroups = () => {
    this.questionGroupeService.findAll().subscribe((data) => {
      this.questionGroups = data;
      this.questionGroupsTechnicalDebt = data.filter(
        (group) => group.type === QuestionGroupeType.technicalDebt
      );
      this.questionGroupsBusinessValue = data.filter(
        (group) => group.type === QuestionGroupeType.businessValue
      );
    });
  };

  updateApplication(id: number, updateApp: UpdateApplication) {
    const { technicalDebtAverage, businessValueAverage } =
      this.calculateWeightedAverage();
    updateApp.noteBusinessValue = businessValueAverage;
    updateApp.noteTechnicalDebt = technicalDebtAverage;
    this.appService.update(id, updateApp).subscribe({
      next: (data) => {
        this.toastService.success('Application notée avec succès');
        window.location.reload();
      },
      error: (error) => {
        console.log('erreur de la modification : ' + error);
        this.toastService.error("Erreur lors de la notation de l'application");
      },
    });
    console.log(technicalDebtAverage, businessValueAverage);
  }

  ngOnInit() {
    this.findAllQuestionGroups();
  }
}
