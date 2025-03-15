import { Component, ElementRef, HostListener } from '@angular/core';
import {
  CategoryODAChild,
  CategoryODAParent,
} from '../../../../../application/category.type';
import { CommonModule } from '@angular/common';
import { ModalStateService } from '../../modal-state.service';
import { IconTriangleUpComponent } from '../../../../../components/icons/icon-triangle-up/icon-triangle-up.component';
import { IconTriangleDownComponent } from '../../../../../components/icons/icon-triangle-down/icon-triangle-down.component';
import { CategoryODAParentService } from '../../../../category-oda/service/category-oda-parent.service';

@Component({
  selector: 'app-select-category',
  imports: [CommonModule, IconTriangleUpComponent, IconTriangleDownComponent],
  templateUrl: './select-category.component.html',
  styleUrl: './select-category.component.scss',
})
export class SelectCategoryComponent {
  categories: CategoryODAParent[] = [];

  isOpen = false;
  selectedChild: CategoryODAChild | null = null;
  openParents: Set<number> = new Set();
  parent: CategoryODAParent | undefined;

  constructor(
    private elementRef: ElementRef,
    private categoryODAParentService: CategoryODAParentService,
    public modalStateService: ModalStateService
  ) {}

  ngOnInit(): void {
    this.categoryODAParentService.findAll().subscribe((categories) => {
      this.categories = categories;
      for (const parent of categories) {
        for (const child of parent.childs) {
          if (child.id == this.modalStateService.createApplication.categoryId) {
            this.selectedChild = child;
            return;
          }
        }
      }
    });
  }

  toggleDropdown(): void {
    this.isOpen = !this.isOpen;
  }

  toggleParent(parent: CategoryODAParent): void {
    if (this.openParents.has(parent.id)) {
      this.openParents.delete(parent.id);
    } else {
      this.openParents.add(parent.id);
    }
  }

  isParentOpen(parent: CategoryODAParent): boolean {
    return this.openParents.has(parent.id);
  }

  selectCategory(child: CategoryODAChild): void {
    this.modalStateService.createApplication.categoryId = child.id;
    this.selectedChild = child;
    this.isOpen = false;
    this.openParents.clear();
  }

  @HostListener('document:click', ['$event'])
  onClickOutside(event: Event) {
    if (!this.elementRef.nativeElement.contains(event.target)) {
      this.isOpen = false;
    }
  }
}
