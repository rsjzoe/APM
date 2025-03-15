import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconClassificationComponent } from './icon-classification.component';

describe('IconClassificationComponent', () => {
  let component: IconClassificationComponent;
  let fixture: ComponentFixture<IconClassificationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconClassificationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconClassificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
