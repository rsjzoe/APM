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
  @Input() questionId = 0;
  @Input() questionGroupId: number = 0;

  @Input() handleRatingWithIds: (rating: number, groupId: number, questionId: number) => void = () => {};
  stars = [1, 2, 3, 4, 5];
  rate(rating: number): void {
    this.rating = rating;
    this.handleRatingWithIds(rating, this.questionGroupId, this.questionId);
  }
}
