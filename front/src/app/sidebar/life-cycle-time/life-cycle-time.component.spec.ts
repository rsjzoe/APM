import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LifeCycleTimeComponent } from './life-cycle-time.component';

describe('LifeCycleTimeComponent', () => {
  let component: LifeCycleTimeComponent;
  let fixture: ComponentFixture<LifeCycleTimeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LifeCycleTimeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LifeCycleTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
