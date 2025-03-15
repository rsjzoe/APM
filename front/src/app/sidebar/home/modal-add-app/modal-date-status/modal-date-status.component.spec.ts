import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalDateStatusComponent } from './modal-date-status.component';

describe('ModalDateStatusComponent', () => {
  let component: ModalDateStatusComponent;
  let fixture: ComponentFixture<ModalDateStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalDateStatusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalDateStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
