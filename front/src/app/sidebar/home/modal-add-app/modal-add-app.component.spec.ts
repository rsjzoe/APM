import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAddAppComponent } from './modal-add-app.component';

describe('ModalAddAppComponent', () => {
  let component: ModalAddAppComponent;
  let fixture: ComponentFixture<ModalAddAppComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalAddAppComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalAddAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
