import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectDepartementComponent } from './select-departement.component';

describe('SelectDepartementComponent', () => {
  let component: SelectDepartementComponent;
  let fixture: ComponentFixture<SelectDepartementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SelectDepartementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelectDepartementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
