import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconDepartementComponent } from './icon-departement.component';

describe('IconDepartementComponent', () => {
  let component: IconDepartementComponent;
  let fixture: ComponentFixture<IconDepartementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconDepartementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconDepartementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
