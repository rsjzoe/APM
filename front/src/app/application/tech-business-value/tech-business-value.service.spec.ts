import { TestBed } from '@angular/core/testing';

import { TechBusinessValueService } from './tech-business-value.service';

describe('TechBusinessValueService', () => {
  let service: TechBusinessValueService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TechBusinessValueService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
