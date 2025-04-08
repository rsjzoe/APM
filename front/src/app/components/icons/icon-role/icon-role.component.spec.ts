import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconRoleComponent } from './icon-role.component';

describe('IconRoleComponent', () => {
  let component: IconRoleComponent;
  let fixture: ComponentFixture<IconRoleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconRoleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconRoleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
