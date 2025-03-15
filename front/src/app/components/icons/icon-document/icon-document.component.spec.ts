import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconDocumentComponent } from './icon-document.component';

describe('IconDocumentComponent', () => {
  let component: IconDocumentComponent;
  let fixture: ComponentFixture<IconDocumentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconDocumentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconDocumentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
