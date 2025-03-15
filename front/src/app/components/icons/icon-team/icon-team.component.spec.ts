import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconTeamComponent } from './icon-team.component';

describe('IconTeamComponent', () => {
  let component: IconTeamComponent;
  let fixture: ComponentFixture<IconTeamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconTeamComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconTeamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
