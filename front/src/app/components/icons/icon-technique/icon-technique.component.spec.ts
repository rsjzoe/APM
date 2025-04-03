import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconTechniqueComponent } from './icon-technique.component';

describe('IconTechniqueComponent', () => {
  let component: IconTechniqueComponent;
  let fixture: ComponentFixture<IconTechniqueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconTechniqueComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconTechniqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
