import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccordionValeurCoutComponent } from './accordion-valeur-cout.component';

describe('AccordionValeurCoutComponent', () => {
  let component: AccordionValeurCoutComponent;
  let fixture: ComponentFixture<AccordionValeurCoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccordionValeurCoutComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccordionValeurCoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
