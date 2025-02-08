import { Component, Input, SimpleChanges } from '@angular/core';
import {
  Application,
  Category,
  Departement,
} from '../../../../application-APM/appType';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ValueFromGeneral } from '../modal-type';
import { CategoryService } from '../../../category/category.service';
import { DepartementService } from '../../../../application-APM/departement.service';

@Component({
  selector: 'app-modal-general',
  imports: [FormsModule, CommonModule],
  templateUrl: './modal-general.component.html',
  styleUrl: './modal-general.component.scss',
})
export class ModalGeneralComponent {
  appName: string = '';
  description = '';
  categoryId!: number;
  categories: Category[] = [];
  departements: Departement[] = [];
  departementId!: number;
  @Input() appEditing: Application | null = null;

  @Input() getValueFromGeneral(value: ValueFromGeneral) {}

  constructor(
    private categoryService: CategoryService,
    private departementService: DepartementService
  ) {}

  findAllCategory = () => {
    this.categoryService.findAll().subscribe({
      next: (data) => {
        this.categories = data;
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des tâches :', error);
      },
    });
  };

  findAllDepartement = () => {
    this.departementService.findAll().subscribe({
      next: (data) => {
        this.departements = data;
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des tâches :', error);
      },
    });
  };

  ngOnChanges(changes: SimpleChanges) {
    const currenAppEditing: Application | null = changes['appEditing'].currentValue;
    if (currenAppEditing == null) {
      return;
    }
    this.appName = currenAppEditing.name;
    this.description = currenAppEditing.description;
    this.categoryId = currenAppEditing.category.id;
    this.departementId = currenAppEditing.departement.id;
  }

  ngOnInit() {
    this.findAllCategory();
    this.findAllDepartement();
  }
}
