import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RateApplicationComponent } from './rate-application.component';

describe('RateApplicationComponent', () => {
  let component: RateApplicationComponent;
  let fixture: ComponentFixture<RateApplicationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RateApplicationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RateApplicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
