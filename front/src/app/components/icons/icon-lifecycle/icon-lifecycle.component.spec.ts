import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconLifecycleComponent } from './icon-lifecycle.component';

describe('IconLifecycleComponent', () => {
  let component: IconLifecycleComponent;
  let fixture: ComponentFixture<IconLifecycleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconLifecycleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconLifecycleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
