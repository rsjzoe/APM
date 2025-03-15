import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController,
  provideHttpClientTesting,
} from '@angular/common/http/testing';
import { CategoryODAChildService } from './category-oda-child.service';
import { CategoryODAChildBackend } from '../../../application/appBackend';

describe('CategoryODAChildService', () => {
  let service: CategoryODAChildService;
  let httpMock: HttpTestingController;
  const apiUrl = 'http://localhost:8080/category-oda-child';

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CategoryODAChildService, provideHttpClientTesting()],
    });

    service = TestBed.inject(CategoryODAChildService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get all categories', () => {
    const mockCategories: CategoryODAChildBackend[] = [
      { id: 1, name: 'Category 1' },
      { id: 2, name: 'Category 2' },
    ];

    service.findAll().subscribe((categories) => {
      expect(categories.length).toBe(2);
      expect(categories).toEqual(mockCategories);
    });

    const req = httpMock.expectOne(apiUrl);
    expect(req.request.method).toBe('GET');
    req.flush(mockCategories);
  });

  it('should add a new category', () => {
    const newCategory = { parentId: 1, name: 'Nouvelle Catégorie' };
    const mockResponse = { id: 1, name: 'Nouvelle Catégorie' };

    service.add(newCategory).subscribe((category) => {
      expect(category.name).toBe('Nouvelle Catégorie');
    });

    const req = httpMock.expectOne(apiUrl);
    expect(req.request.method).toBe('POST');
    req.flush(mockResponse);
  });

  it('should delete a category', () => {
    const categoryId = 1;

    service.delete(categoryId).subscribe((response) => {
      expect(response).toBeTruthy();
    });

    const req = httpMock.expectOne(`${apiUrl}/${categoryId}`);
    expect(req.request.method).toBe('DELETE');
    req.flush({});
  });

  it('should update a category', () => {
    const categoryId = 1;
    const updatedCategory = { parentId: 1, name: 'Catégorie Modifiée' };
    const mockResponse = { id: 1, name: 'Catégorie Modifiée' };

    service.update(categoryId, updatedCategory).subscribe((category) => {
      expect(category.name).toBe('Catégorie Modifiée');
    });

    const req = httpMock.expectOne(`${apiUrl}/${categoryId}`);
    expect(req.request.method).toBe('PUT');
    req.flush(mockResponse);
  });

  it('should find a category by id', () => {
    const categoryId = 1;
    const mockCategory = { id: 1, name: 'Category 1' };

    service.findById(categoryId).subscribe((category) => {
      expect(category.name).toBe('Category 1');
    });

    const req = httpMock.expectOne(`${apiUrl}/${categoryId}`);
    expect(req.request.method).toBe('GET');
    req.flush(mockCategory);
  });
});
