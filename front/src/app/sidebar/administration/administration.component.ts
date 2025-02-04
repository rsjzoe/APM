// import { Component } from '@angular/core';
import { ButtonComponent } from '../../components/button/button.component';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Role, User } from './user.type';
import { UserModalComponent } from './user-modal/user-modal.component';

@Component({
  selector: 'app-administration',
  imports: [ButtonComponent, CommonModule, FormsModule, UserModalComponent],
  templateUrl: './administration.component.html',
  styleUrl: './administration.component.scss',
})
export class AdministrationComponent {
  users: User[] = [];
  isEditing: number | null = null;
  userEditing: User | null = null;

  addUser = (name: string, trigramme: string, role: Role) => {
    this.users.push({
      id: Date.now(),
      name: name,
      trigramme: trigramme,
      role,
    });
  };

  deleteUser(id: number) {
    this.users = this.users.filter((user) => user.id !== id);
  }

  editeUser(user: User) {
    this.isEditing = user.id;
    this.userEditing = user;
  }

  updateUser = (name: string, trigramme: string, role: Role) => {
    if (this.isEditing == null) return;
    const updatedUser: User = {
      id: this.isEditing,
      name: name,
      trigramme: trigramme,
      role,
    };

    for (let user of this.users) {
      if (user.id == updatedUser.id) {
        user.name = updatedUser.name;
        user.trigramme = updatedUser.trigramme;
        user.role = updatedUser.role;
      }
    }
    this.isEditing = null;
    this.userEditing = null;
  };
}
