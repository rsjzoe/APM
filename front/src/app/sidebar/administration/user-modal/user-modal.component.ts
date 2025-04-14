import { CommonModule } from '@angular/common';
import { Component, Input, SimpleChanges } from '@angular/core';
import { User } from '../user.type';
import { FormsModule } from '@angular/forms';
import { Departement } from '../../../application/departement/departement.type';
import { DepartementService } from '../../../application/departement/departement.service';

@Component({
  selector: 'app-user-modal',
  imports: [CommonModule, FormsModule],
  templateUrl: './user-modal.component.html',
  styleUrl: './user-modal.component.scss',
})
export class UserModalComponent {
  @Input() isEditing: string | null = null;
  newUserName = '';
  newUserTrigramme = '';
  newUserDepartement = '';
  departements: Departement[] = [];

  constructor(private departementService: DepartementService) {}

  @Input() userEditing: User | null = null;
  newUserRole: string = 'visitor';
  @Input() addUser = (
    name: string,
    trigramme: string,
    departement: string,
    role: string
  ) => {};
  @Input() updateUser = (
    name: string,
    trigramme: string,
    departement: string,
    role: string
  ) => {};

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

  add() {
    if (this.newUserName.length == 0 || this.newUserTrigramme.length == 0)
      return;
    this.addUser(
      this.newUserName,
      this.newUserTrigramme,
      this.newUserDepartement,
      this.newUserRole
    );
    this.newUserName = '';
    this.newUserTrigramme = '';
    this.newUserDepartement = '';
    this.newUserRole = 'visitor';
  }

  update() {
    if (this.newUserName.length == 0 || this.newUserTrigramme.length == 0)
      return;
    this.updateUser(
      this.newUserName,
      this.newUserTrigramme,
      this.newUserDepartement,
      this.newUserRole
    );
    this.newUserName = '';
    this.newUserTrigramme = '';
    this.newUserDepartement = '';
    this.newUserRole = 'visitor';
  }

  ngOnChanges(changes: SimpleChanges) {
    const currentUserEditing: User | null = changes['userEditing'].currentValue;
    if (currentUserEditing == null) {
      return;
    }
    this.newUserName = currentUserEditing.name;
    this.newUserTrigramme = currentUserEditing.trigramme;
    this.newUserDepartement = currentUserEditing.departement;
    this.newUserRole = currentUserEditing.role;
  }
  ngOnInit() {
    this.findAllDepartement();
  }
}
