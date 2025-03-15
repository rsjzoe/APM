import { Component } from '@angular/core';
import { DepartementService } from '../../../../application/departement.service';
import { ModalStateService } from '../../modal-add-app/modal-state.service';
import { ClasseService } from '../../../../application/classe.service';
import { SelectCategoryComponent } from '../../modal-add-app/modal-general/select-category/select-category.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Departement } from '../../../../application/departement.type';
import { Classe } from '../../../../application/classe.type';

@Component({
  selector: 'app-form-general',
  imports: [FormsModule, CommonModule, SelectCategoryComponent],
  templateUrl: './form-general.component.html',
  styleUrl: './form-general.component.scss',
})
export class FormGeneralComponent {
  departements: Departement[] = [];
  classes: Classe[] = [];

  constructor(
    private departementService: DepartementService,
    public modalStateService: ModalStateService,
    private classeService: ClasseService
  ) {}

  findAllClasse = () => {
    this.classeService.findAll().subscribe({
      next: (data) => {
        this.classes = data;
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
    this.findAllClasse();
    this.findAllDepartement();
  }
}
