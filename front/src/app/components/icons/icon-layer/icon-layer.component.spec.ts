import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconLayerComponent } from './icon-layer.component';

describe('IconLayerComponent', () => {
  let component: IconLayerComponent;
  let fixture: ComponentFixture<IconLayerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IconLayerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconLayerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
