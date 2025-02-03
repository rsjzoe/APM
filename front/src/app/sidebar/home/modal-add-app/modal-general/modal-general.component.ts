import { Component, Input } from '@angular/core';
import { Category, Departement } from '../../../../application-APM/appType';
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
  categorieId!: number;
  categories: Category[] = [];
  departements: Departement[] = [];
  departementId!: number;

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

  ngOnInit() {
    this.findAllCategory();
    this.findAllDepartement();
  }
}
