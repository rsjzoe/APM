import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccordionGeneralComponent } from './accordion-general.component';

describe('AccordionGeneralComponent', () => {
  let component: AccordionGeneralComponent;
  let fixture: ComponentFixture<AccordionGeneralComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccordionGeneralComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccordionGeneralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
