import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TimelineHistoryComponent } from './timeline-history.component';

describe('TimelineHistoryComponent', () => {
  let component: TimelineHistoryComponent;
  let fixture: ComponentFixture<TimelineHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TimelineHistoryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TimelineHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
