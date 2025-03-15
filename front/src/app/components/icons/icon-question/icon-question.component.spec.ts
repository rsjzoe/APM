import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconQuestionComponent } from './icon-question.component';

describe('IconQuestionComponent', () => {
  let component: IconQuestionComponent;
  let fixture: ComponentFixture<IconQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconQuestionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
