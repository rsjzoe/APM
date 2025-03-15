import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccordionDateStatusComponent } from './accordion-date-status.component';

describe('AccordionDateStatusComponent', () => {
  let component: AccordionDateStatusComponent;
  let fixture: ComponentFixture<AccordionDateStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccordionDateStatusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccordionDateStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
