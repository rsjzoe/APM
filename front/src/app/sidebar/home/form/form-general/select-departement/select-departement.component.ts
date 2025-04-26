import { Component, ElementRef, HostListener } from '@angular/core';
import { Departement } from '../../../../../application/departement/departement.type';
import {
  debounceTime,
  distinctUntilChanged,
  Observable,
  of,
  Subject,
  switchMap,
} from 'rxjs';
import { DepartementService } from '../../../../../application/departement/departement.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ModalStateService } from '../../../modal-add-app/modal-state.service';
import { IconTriangleDownComponent } from "../../../../../components/icons/icon-triangle-down/icon-triangle-down.component";
import { IconTriangleUpComponent } from "../../../../../components/icons/icon-triangle-up/icon-triangle-up.component";

@Component({
  selector: 'app-select-departement',
  imports: [CommonModule, FormsModule, IconTriangleDownComponent, IconTriangleUpComponent],
  templateUrl: './select-departement.component.html',
  styleUrl: './select-departement.component.scss',
})
export class SelectDepartementComponent {
  allDepartements: Departement[] = [];
  filteredDepartements: Departement[] = [];
  searchTerm = '';
  dropdownOpen = false;

  private searchTerms = new Subject<string>();

  constructor(
    private departementService: DepartementService,
    private elementRef: ElementRef,
    public modalStateService: ModalStateService
  ) {}

  @HostListener('document:click', ['$event'])
  clickOutside(event: Event) {
    if (!this.elementRef.nativeElement.contains(event.target)) {
      this.dropdownOpen = false;
    }
  }

  ngOnInit(): void {
    this.departementService.findAll().subscribe((departments) => {
      this.allDepartements = departments;
      this.filteredDepartements = [...departments];
    });

    this.searchTerms
      .pipe(
        debounceTime(300),
        distinctUntilChanged(),
        switchMap((term) => this.searchDepartements(term))
      )
      .subscribe((departments) => {
        this.filteredDepartements = departments;
      });
  }

  onSearch(term: string): void {
    this.searchTerms.next(term);
  }

  toggleDropdown(): void {
    this.dropdownOpen = !this.dropdownOpen;
    if (this.dropdownOpen) {
      this.resetSearch();
    }
  }

  resetSearch(): void {
    this.searchTerm = '';
    this.filteredDepartements = [...this.allDepartements];
  }

  selectDepartement(dept: Departement): void {
    if (!this.isSelected(dept)) {
      this.modalStateService.createApplication.selectedDepartements.push(dept);
      // Close dropdown after selection
      this.dropdownOpen = false;
    }
  }

  removeDepartement(dept: Departement): void {
    this.modalStateService.createApplication.selectedDepartements =
      this.modalStateService.createApplication.selectedDepartements.filter(
        (d) => d.id !== dept.id
      );
  }

  isSelected(dept: Departement): boolean {
    return this.modalStateService.createApplication.selectedDepartements.some(
      (d) => d.id === dept.id
    );
  }

  searchDepartements(term: string): Observable<Departement[]> {
    if (!term.trim()) {
      return of(this.allDepartements);
    }

    const filteredDepts = this.allDepartements.filter((dept) =>
      dept.name.toLowerCase().includes(term.toLowerCase())
    );

    return of(filteredDepts);
  }
}
