import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconSablierComponent } from './icon-sablier.component';

describe('IconSablierComponent', () => {
  let component: IconSablierComponent;
  let fixture: ComponentFixture<IconSablierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconSablierComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconSablierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
