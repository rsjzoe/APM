import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  CategoryODAChild,
  CategoryODAParent,
  CreateCategoryODAChild,
  CreateCategoryODAParent,
  UpdateCategoryODAChild,
  UpdateCategoryODAParent,
} from '../../application/category/category.type';
import { FormsModule } from '@angular/forms';
import { CategoryODAParentService } from './service/category-oda-parent.service';
import { CategoryODAChildService } from './service/category-oda-child.service';
import { IconEditComponent } from '../../components/icons/icon-edit/icon-edit.component';
import { IconDeleteComponent } from '../../components/icons/icon-delete/icon-delete.component';
import { IconPlusComponent } from '../../components/icons/icon-plus/icon-plus.component';
import { ModalConfirmComponent } from '../../components/modal-confirm/modal-confirm.component';
import { Observable } from 'rxjs';
import { UserService } from '../administration/user.service';

@Component({
  selector: 'app-category-oda',
  imports: [
    CommonModule,
    FormsModule,
    IconEditComponent,
    IconDeleteComponent,
    IconPlusComponent,
    ModalConfirmComponent,
  ],
  templateUrl: './category-oda.component.html',
  styleUrl: './category-oda.component.scss',
})
export class CategoryOdaComponent {
  categories: CategoryODAParent[] = [];
  newParentName: string = '';
  newChildNames: { [key: number]: string } = {};

  // Edit state management
  editingParent: { [key: number]: boolean } = {};
  editingChild: { [key: number]: boolean } = {};
  editingParentName: { [key: number]: string } = {};
  editingChildName: { [key: number]: string } = {};
  categoryIdDelete: number | null = null;
  openIndex: number | null = null;
  categoryIdChild: number | null = null;

  canAddCategory$!: Observable<boolean>;
  canEditCategory$!: Observable<boolean>;
  canDeleteCategory$!: Observable<boolean>;

  constructor(
    private categoryODAParentService: CategoryODAParentService,
    private categoryODAChildService: CategoryODAChildService,
    private userService: UserService
  ) {}

  generateRandomColor(): string {
    const colors = [
      '#004080',
      '#1DA1F2',
      '#E1306C',
      '#b98f11',
      '#6e4aa7',
      '#198c51',
      '#000000',
    ];
    return colors[Math.floor(Math.random() * colors.length)];
  }

  ngOnInit() {
    this.canAddCategory$ = this.userService.canCreateService('category');
    this.canEditCategory$ = this.userService.canEditService('category');
    this.canDeleteCategory$ = this.userService.canDeleteService('category');
    this.categoryODAParentService.findAll().subscribe((categories) => {
      this.categories = categories;
    });
  }

  toggleAccordion(index: number) {
    this.openIndex = this.openIndex === index ? null : index;
  }

  addParentCategory() {
    if (this.newParentName.trim()) {
      const newCategory: CreateCategoryODAParent = {
        name: this.newParentName,
        bgColor: this.generateRandomColor(),
      };

      this.categoryODAParentService.add(newCategory).subscribe({
        next: (val) => {
          this.newParentName = '';
          this.categories.push(val);
        },
      });
    }
  }

  saveCategoryIdDelete = (idCategory: number) => {
    this.categoryIdDelete = idCategory;
  };

  saveCategoryIdChild = (idChild: number) => {
    this.categoryIdChild = idChild;
  };

  onConfirmDelete = () => {
    if (this.categoryIdDelete) {
      this.deleteParentCategory(this.categoryIdDelete);
    }
  };

  onConfirmDeleteChild = () => {
    console.log('ID category delete', this.categoryIdChild);
    if (this.categoryIdChild) {
      this.deleteChildCategory(this.categoryIdChild);
    }
  };

  deleteParentCategory(id: number) {
    this.categoryODAParentService.delete(id).subscribe({
      next: () => {
        this.refresh();
      },
    });
  }

  addChildCategory(parentId: number) {
    const childName = this.newChildNames[parentId];
    if (childName?.trim()) {
      const newChild: CreateCategoryODAChild = {
        parentId,
        name: childName,
      };
      this.categoryODAChildService.add(newChild).subscribe({
        next: (val) => {
          this.newChildNames[parentId] = '';
          for (let categorie of this.categories) {
            if (parentId == categorie.id) {
              categorie.childs.push(val);
            }
          }
        },
      });
    }
  }

  deleteChildCategory(childId: number) {
    this.categoryODAChildService.delete(childId).subscribe({
      next: () => {
        this.refresh();
      },
    });
  }

  // Parent editing methods
  startEditingParent(category: CategoryODAParent) {
    this.editingParent[category.id] = true;
    this.editingParentName[category.id] = category.name;
  }

  saveParentName(category: CategoryODAParent) {
    if (this.editingParent[category.id]) {
      const newName = this.editingParentName[category.id]?.trim();
      if (newName) {
        const updatedCategory: UpdateCategoryODAParent = {
          ...category,
          name: newName,
        };
        this.categoryODAParentService
          .update(category.id, updatedCategory)
          .subscribe({
            next: () => {
              this.refresh();
            },
          });
      }
      this.editingParent[category.id] = false;
    }
  }

  // Child editing methods
  startEditingChild(parentId: number, child: CategoryODAChild) {
    this.editingChild[child.id] = true;
    this.editingChildName[child.id] = child.name;
  }

  saveChildName(parentId: number, child: CategoryODAChild) {
    if (this.editingChild[child.id]) {
      const newName = this.editingChildName[child.id]?.trim();
      if (newName) {
        const parent = this.categories.find((c) => c.id === parentId);
        if (parent) {
          const updatedChild: UpdateCategoryODAChild = {
            ...child,
            parentId,
            name: newName,
          };
          this.categoryODAChildService
            .update(child.id, updatedChild)
            .subscribe({
              next: () => {
                this.refresh();
              },
            });
        }
      }
      this.editingChild[child.id] = false;
    }
  }

  refresh = () => {
    this.categoryODAParentService.findAll().subscribe((categories) => {
      this.categories = categories;
    });
  };
}
