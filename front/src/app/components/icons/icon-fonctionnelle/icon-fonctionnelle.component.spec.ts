import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconFonctionnelleComponent } from './icon-fonctionnelle.component';

describe('IconFonctionnelleComponent', () => {
  let component: IconFonctionnelleComponent;
  let fixture: ComponentFixture<IconFonctionnelleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconFonctionnelleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconFonctionnelleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
