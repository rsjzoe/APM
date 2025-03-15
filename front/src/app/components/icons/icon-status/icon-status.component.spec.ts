import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconStatusComponent } from './icon-status.component';

describe('IconStatusComponent', () => {
  let component: IconStatusComponent;
  let fixture: ComponentFixture<IconStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconStatusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
