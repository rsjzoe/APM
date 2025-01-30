import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-star',
  imports: [],
  templateUrl: './star.component.html',
  styleUrl: './star.component.scss',
})
export class StarComponent {
  @Input() filled = false;
  @Input() value!: number;
  @Input() onRating(value: number) {}

  getStarClass(): string {
    return this.filled ? 'filled' : 'empty';
  }

}
