import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAddDocumentationComponent } from './modal-add-documentation.component';

describe('ModalAddDocumentationComponent', () => {
  let component: ModalAddDocumentationComponent;
  let fixture: ComponentFixture<ModalAddDocumentationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalAddDocumentationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalAddDocumentationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
