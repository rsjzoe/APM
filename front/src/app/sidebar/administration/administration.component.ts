// import { Component } from '@angular/core';
import { ButtonComponent } from '../../components/button/button.component';
import { CommonModule } from '@angular/common';
import { Component, ViewEncapsulation } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Role, User } from './user.type';
import { UserModalComponent } from './user-modal/user-modal.component';
import { AuthService } from '../../auth/auth.service';
import { UserService } from './user.service';
import { ModalConfirmComponent } from '../../components/modal-confirm/modal-confirm.component';

@Component({
  selector: 'app-administration',
  imports: [
    ButtonComponent,
    CommonModule,
    FormsModule,
    UserModalComponent,
    ModalConfirmComponent,
  ],
  templateUrl: './administration.component.html',
  styleUrl: './administration.component.scss',
  encapsulation: ViewEncapsulation.None,
})
export class AdministrationComponent {
  users: User[] = [];
  isEditing: string | null = null;
  userEditing: User | null = null;
  appTrigrammeDelete: string | null = null;

  constructor(
    private authService: AuthService,
    private userService: UserService
  ) {}

  saveTrigrammeAppDelete = (trigramme: string) => {
    this.appTrigrammeDelete = trigramme;
  };

  onConfirmDelete = () => {
    if (this.appTrigrammeDelete) {
      this.deleteUser(this.appTrigrammeDelete);
    }
  };

  addUser = (
    name: string,
    trigramme: string,
    departement: string,
    role: Role
  ) => {
    this.authService
      .register({ name, trigramme, departement, role })
      .subscribe({
        next: (val) => {
          this.users.push(val);
        },
      });
  };

  deleteUser(trigramme: string) {
    this.userService.delete(trigramme).subscribe({
      next: () => {
        this.users = this.users.filter((user) => user.trigramme !== trigramme);
      },
      error: (error) => {
        console.log('erreur de la suppresssion : ' + error);
      },
    });
  }

  findAllUsers = () => {
    this.userService.findAll().subscribe({
      next: (data) => {
        this.users = data;
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des tâches :', error);
      },
    });
  };

  editeUser(user: User) {
    this.isEditing = user.trigramme;
    this.userEditing = user;
  }

  updateUser = (
    name: string,
    trigramme: string,
    departement: string,
    role: Role
  ) => {
    if (this.isEditing == null) return;

    this.userService
      .update(this.isEditing, { name, departement, role, trigramme })
      .subscribe({
        next: (val) => {
          for (let user of this.users) {
            if (user.trigramme == val.trigramme) {
              user.name = val.name;
              user.trigramme = val.trigramme;
              user.departement = val.departement;
              user.role = val.role;
            }
          }
          this.isEditing = null;
          this.userEditing = null;
        },
        error: (error) => {
          console.log('erreur de la modification : ' + error);
        },
      });
  };

  refresh = () => {
    this.findAllUsers();
  };

  ngOnInit() {
    this.refresh();
  }
}
