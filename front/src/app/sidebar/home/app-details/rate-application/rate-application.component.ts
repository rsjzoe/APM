import { Component, Input } from '@angular/core';
import { StarRatingComponent } from './star-rating/star-rating.component';
import { CommonModule } from '@angular/common';
import { QuestionGroupeService } from '../../../performance/service/questionGroupe.service';
import {
  Application,
  UpdateApplication,
} from '../../../../application/app.type';
import { ApplicationService } from '../../application.service';
import {
  QuestionGroupe,
  QuestionGroupeType,
} from '../../../../application/question.type';

@Component({
  selector: 'app-rate-application',
  imports: [StarRatingComponent, CommonModule],
  templateUrl: './rate-application.component.html',
  styleUrl: './rate-application.component.scss',
})
export class RateApplicationComponent {
  questionGroups: QuestionGroupe[] = [
    {
      id: 1,
      text: 'Performance',
      borderColor: '#ff7900',
      coeff: 2,
      questions: [
        {
          id: 1,
          text: "L'application répond-elle rapidement aux actions de l'utilisateur?",
        },
        { id: 2, text: 'Les temps de chargement sont-ils acceptables?' },
        {
          id: 3,
          text: "L'application gère-t-elle efficacement les ressources système?",
        },
      ],
      type: QuestionGroupeType.businessValue,
    },
    {
      id: 2,
      text: 'Expérience Utilisateur',
      borderColor: '#32c832',
      coeff: 3,
      questions: [
        { id: 4, text: "L'interface est-elle intuitive et facile à utiliser?" },
      ],
      type: QuestionGroupeType.businessValue,
    },
    {
      id: 3,
      text: 'Fiabilité',
      borderColor: '#0088cc',
      coeff: 2.5,
      questions: [
        { id: 7, text: "L'application fonctionne-t-elle sans erreurs?" },
        { id: 8, text: 'Les données sont-elles correctement sauvegardées?' },
      ],
      type: QuestionGroupeType.technicalDebt,
    },
  ];

  @Input() application!: Application;
  constructor(
    private questionGroupeService: QuestionGroupeService,
    private appService: ApplicationService
  ) {}

  expandedGroups: boolean[] = [true];
  // Store ratings in a map: {groupId: {questionId: rating}}
  ratings: Map<number, Map<number, number>> = new Map();

  isGroupExpanded(index: number): boolean {
    return this.expandedGroups[index] || false;
  }

  toggleGroup(index: number) {
    this.expandedGroups[index] = !this.expandedGroups[index];
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
      noteCost: 0,
      noteTechBusiness: 0,
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

  findAllQuestionGroups = () => {};

  updateApplication(id: number, updateApp: UpdateApplication) {}

  ngOnInit() {
    // this.findAllQuestionGroups();
  }
}
