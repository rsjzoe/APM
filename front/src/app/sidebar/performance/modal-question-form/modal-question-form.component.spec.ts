import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalQuestionFormComponent } from './modal-question-form.component';

describe('ModalQuestionFormComponent', () => {
  let component: ModalQuestionFormComponent;
  let fixture: ComponentFixture<ModalQuestionFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalQuestionFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalQuestionFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
