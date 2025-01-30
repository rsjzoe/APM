import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { StarComponent } from '../../../../../components/icons/star/star.component';

@Component({
  selector: 'app-star-rating',
  imports: [CommonModule, FormsModule, StarComponent],
  templateUrl: './star-rating.component.html',
  styleUrl: './star-rating.component.scss',
})
export class StarRatingComponent {
  @Input() rating = 0;
  @Input() questionIndex = 0;
  @Input() handleRatingWithIndex = (
    rating: number,
    questionIndex: number
  ) => {};
  handleRating = (rating: number) => {
    this.handleRatingWithIndex(rating, this.questionIndex);
  };
}
