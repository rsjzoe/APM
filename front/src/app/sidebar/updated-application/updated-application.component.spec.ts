import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatedApplicationComponent } from './updated-application.component';

describe('UpdatedApplicationComponent', () => {
  let component: UpdatedApplicationComponent;
  let fixture: ComponentFixture<UpdatedApplicationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdatedApplicationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdatedApplicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
