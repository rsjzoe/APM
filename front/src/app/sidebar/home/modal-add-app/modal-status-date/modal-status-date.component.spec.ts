import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalStatusDateComponent } from './modal-status-date.component';

describe('ModalStatusDateComponent', () => {
  let component: ModalStatusDateComponent;
  let fixture: ComponentFixture<ModalStatusDateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalStatusDateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalStatusDateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
