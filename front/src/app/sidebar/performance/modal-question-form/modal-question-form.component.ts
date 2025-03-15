import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { QuestionGroupe } from '../../../application/appType';

@Component({
  selector: 'app-modal-question-form',
  imports: [FormsModule, CommonModule],
  templateUrl: './modal-question-form.component.html',
  styleUrl: './modal-question-form.component.scss',
})
export class ModalQuestionFormComponent {
  @Input() isEditingId: number | null = null;
  valueQuestion!: string;
  valueCoeff!:number;
  @Input() questionEditing: QuestionGroupe | null = null;
  @Input() addQuestion = (question: string,coeff:number) => {};
  @Input() updateQuestion = (question: string,coeff:number) => {};

  add() {
    
    if (this.valueQuestion.length == 0) return;
    this.addQuestion(this.valueQuestion, this.valueCoeff);
    this.valueQuestion = '';
  }

  update() {
    if (this.valueQuestion.length == 0) return;
    this.updateQuestion(this.valueQuestion, this.valueCoeff);
    this.valueQuestion = '';
  }

  ngOnChanges(changes: any) {
    //  anstoina ity isakin miova ny props rai

    const currentQuestionEditing: QuestionGroupe | null =
      changes['questionEditing'].currentValue;
    if (currentQuestionEditing == null) {
      return;
    }
    this.valueQuestion = currentQuestionEditing.text;
  }
}
