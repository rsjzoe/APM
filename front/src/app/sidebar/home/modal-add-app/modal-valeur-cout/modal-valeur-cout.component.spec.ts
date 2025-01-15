import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalValeurCoutComponent } from './modal-valeur-cout.component';

describe('ModalValeurCoutComponent', () => {
  let component: ModalValeurCoutComponent;
  let fixture: ComponentFixture<ModalValeurCoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalValeurCoutComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalValeurCoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
