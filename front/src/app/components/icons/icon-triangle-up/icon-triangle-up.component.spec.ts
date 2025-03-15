import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconTriangleUpComponent } from './icon-triangle-up.component';

describe('IconTriangleUpComponent', () => {
  let component: IconTriangleUpComponent;
  let fixture: ComponentFixture<IconTriangleUpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconTriangleUpComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconTriangleUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
