import { Component } from '@angular/core';
import { StarRatingComponent } from './star-rating/star-rating.component';
import { CommonModule } from '@angular/common';
import { QuestionService } from '../../../performance/question.service';
import { Question } from '../../../../application-APM/appType';

@Component({
  selector: 'app-rate-application',
  imports: [StarRatingComponent, CommonModule],
  templateUrl: './rate-application.component.html',
  styleUrl: './rate-application.component.scss',
})
export class RateApplicationComponent {
  questions: Question[] = [];

  constructor(private questionService: QuestionService) {}

  questionsPerPage = 3;
  currentPage = 0;
  ratings: number[] = new Array(this.questions.length).fill(0);

  getCurrentPage(): number {
    return this.currentPage + 1;
  }

  getTotalPages(): number {
    return Math.ceil(this.questions.length / this.questionsPerPage);
  }

  getCurrentQuestionIndexes(): number[] {
    const startIndex = this.currentPage * this.questionsPerPage;
    const endIndex = Math.min(
      startIndex + this.questionsPerPage,
      this.questions.length
    );
    // [0,1,2]
    // [3,4,5]
    // [6,7,8]
    return Array.from(
      { length: endIndex - startIndex },
      (_, i) => startIndex + i
    );
  }

  handleRating = (rating: number, questionIndex: number) => {
    this.ratings[questionIndex] = rating;
  };

  handleNext() {
    if (this.currentPage < this.getTotalPages() - 1) {
      this.currentPage++;
    }
  }

  handlePrevious() {
    if (this.currentPage > 0) {
      this.currentPage--;
    }
  }

  canProceed(): boolean {
    const currentIndexes = this.getCurrentQuestionIndexes();
    // tokony > 0 daholo ny noten ireo question mipoitra
    // every : boucleny ilay element rehetra de verifieny ilay condition,
    // raha mahamarina daholo dia mi-retourne true ilay every, sinon false
    return currentIndexes.every((index) => this.ratings[index] > 0);
  }

  isLastPage(): boolean {
    return this.currentPage === this.getTotalPages() - 1;
  }

  canSubmit(): boolean {
    return this.ratings.every((rating) => rating > 0);
  }

  handleSubmit() {
    console.log('Submitting ratings:', this.ratings);
    console.log(this.averageRatings());
    
    // Here you would typically send the ratings to your backend
  }

  averageRatings(){
    let sum = 0;
    for(let rating of this.ratings){
      sum += rating;
    }
    return sum / 5;
  }

  findAll = () => {
    this.questionService.findAll().subscribe({
      next: (data) => {
        this.questions = data;
        this.ratings = new Array(this.questions.length).fill(0);
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
