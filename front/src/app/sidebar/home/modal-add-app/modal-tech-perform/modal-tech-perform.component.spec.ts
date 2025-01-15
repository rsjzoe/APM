import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalTechPerformComponent } from './modal-tech-perform.component';

describe('ModalTechPerformComponent', () => {
  let component: ModalTechPerformComponent;
  let fixture: ComponentFixture<ModalTechPerformComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalTechPerformComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalTechPerformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
