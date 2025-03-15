import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-view-pdf',
  imports: [CommonModule],
  templateUrl: './view-pdf.component.html',
  styleUrl: './view-pdf.component.scss',
})
export class ViewPdfComponent {
  @Input() src: string | null = null;
}
