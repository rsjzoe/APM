import { CommonModule } from '@angular/common';
import { Component, Input, SimpleChanges } from '@angular/core';
import { User } from '../user.type';
import { FormsModule } from '@angular/forms';
import { Departement } from '../../../application/departement/departement.type';
import { DepartementService } from '../../../application/departement/departement.service';
import { RoleService } from '../../role/service/role.service';
import { Role } from '../../../application/role/role.type';
import { ModalComponent } from '../../../components/modal/modal.component';

@Component({
  selector: 'app-user-modal',
  imports: [CommonModule, FormsModule, ModalComponent],
  templateUrl: './user-modal.component.html',
  styleUrl: './user-modal.component.scss',
})
export class UserModalComponent {
  @Input() isModalOpen = false;
  @Input() isEditing: string | null = null;
  newUserName = '';
  newUserTrigramme = '';
  newUserDepartement = '';
  departements: Departement[] = [];

  @Input() userEditing: User | null = null;
  roles: Role[] = [];
  newUserRole!: string;
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
  @Input() setIsModalOpen = (value: boolean) => {};

  constructor(
    private departementService: DepartementService,
    private roleService: RoleService
  ) {}

  findAllRole = () => {
    this.roleService.findAll().subscribe({
      next: (data) => {
        this.roles = data;
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
    if (changes['userEditing']) {
      const currentUserEditing: User | null =
        changes['userEditing'].currentValue;
      if (currentUserEditing == null) {
        return;
      }
      this.newUserName = currentUserEditing.name;
      this.newUserTrigramme = currentUserEditing.trigramme;
      this.newUserDepartement = currentUserEditing.departement;
      this.newUserRole = currentUserEditing.role;
    }
  }
  ngOnInit() {
    this.findAllDepartement();
    this.findAllRole();
  }
}
