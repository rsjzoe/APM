import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormDateStatusComponent } from './form-date-status.component';

describe('FormDateStatusComponent', () => {
  let component: FormDateStatusComponent;
  let fixture: ComponentFixture<FormDateStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormDateStatusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormDateStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
