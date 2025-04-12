import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccordionOtherComponent } from './accordion-other.component';

describe('AccordionOtherComponent', () => {
  let component: AccordionOtherComponent;
  let fixture: ComponentFixture<AccordionOtherComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccordionOtherComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccordionOtherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
