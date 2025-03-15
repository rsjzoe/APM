import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconAdminComponent } from './icon-admin.component';

describe('IconAdminComponent', () => {
  let component: IconAdminComponent;
  let fixture: ComponentFixture<IconAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
