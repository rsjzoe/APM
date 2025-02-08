import { Component } from '@angular/core';
import { Category, Departement } from '../../../../application-APM/appType';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CategoryService } from '../../../category/category.service';
import { DepartementService } from '../../../../application-APM/departement.service';
import { ModalStateService } from '../modal-state.service';

@Component({
  selector: 'app-modal-general',
  imports: [FormsModule, CommonModule],
  templateUrl: './modal-general.component.html',
  styleUrl: './modal-general.component.scss',
})
export class ModalGeneralComponent {
  categories: Category[] = [];
  departements: Departement[] = [];

  constructor(
    private categoryService: CategoryService,
    private departementService: DepartementService,
    public modalStateService: ModalStateService
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
