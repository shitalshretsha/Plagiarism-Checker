import { TestBed } from '@angular/core/testing';

import { OnuploadService } from './onupload.service';

describe('OnuploadService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OnuploadService = TestBed.get(OnuploadService);
    expect(service).toBeTruthy();
  });
});
