import { TestBed } from '@angular/core/testing';

import { CategoryODAParentService } from './category-oda-parent.service';

describe('CategoryODAParentService', () => {
  let service: CategoryODAParentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CategoryODAParentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
