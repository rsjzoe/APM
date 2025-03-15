import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalEditAppComponent } from './modal-edit-app.component';

describe('ModalEditAppComponent', () => {
  let component: ModalEditAppComponent;
  let fixture: ComponentFixture<ModalEditAppComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalEditAppComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalEditAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
