import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconTriangleDownComponent } from './icon-triangle-down.component';

describe('IconTriangleDownComponent', () => {
  let component: IconTriangleDownComponent;
  let fixture: ComponentFixture<IconTriangleDownComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconTriangleDownComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconTriangleDownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
