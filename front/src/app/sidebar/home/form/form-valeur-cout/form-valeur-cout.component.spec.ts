import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormValeurCoutComponent } from './form-valeur-cout.component';

describe('FormValeurCoutComponent', () => {
  let component: FormValeurCoutComponent;
  let fixture: ComponentFixture<FormValeurCoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormValeurCoutComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormValeurCoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
