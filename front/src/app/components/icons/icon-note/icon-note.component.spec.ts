import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconNoteComponent } from './icon-note.component';

describe('IconNoteComponent', () => {
  let component: IconNoteComponent;
  let fixture: ComponentFixture<IconNoteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconNoteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconNoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
