import { TestBed } from '@angular/core/testing';

// import { CategoryParentService } from './category-parent.service';
import { CategoryODAParentService } from './service/category-oda-parent.service';

describe('CategoryParentService', () => {
  let service: CategoryODAParentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CategoryODAParentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
