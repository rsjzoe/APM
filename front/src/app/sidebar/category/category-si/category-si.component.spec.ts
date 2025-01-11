import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategorySiComponent } from './category-si.component';

describe('CategorySiComponent', () => {
  let component: CategorySiComponent;
  let fixture: ComponentFixture<CategorySiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategorySiComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CategorySiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
