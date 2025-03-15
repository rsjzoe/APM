import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryOdaComponent } from './category-oda.component';

describe('CategoryOdaComponent', () => {
  let component: CategoryOdaComponent;
  let fixture: ComponentFixture<CategoryOdaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoryOdaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CategoryOdaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
