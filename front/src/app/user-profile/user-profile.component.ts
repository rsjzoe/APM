import { Component } from '@angular/core';
import { User } from '../sidebar/administration/user.type';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IconAdminComponent } from '../components/icons/icon-admin/icon-admin.component';
import { IconDepartementComponent } from '../components/icons/icon-departement/icon-departement.component';
import { IconNoteComponent } from '../components/icons/icon-note/icon-note.component';
import { IconUserComponent } from '../components/icons/icon-user/icon-user.component';
import { IconRoleComponent } from '../components/icons/icon-role/icon-role.component';

@Component({
  selector: 'app-user-profile',
  imports: [
    FormsModule,
    CommonModule,
    IconDepartementComponent,
    IconUserComponent,
    IconRoleComponent,
  ],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.scss',
})
export class UserProfileComponent {
  user: User = {
    name: 'Jean Dupont',
    trigramme: 'JDP',
    departement: 'Engineering',
    role: 'visitor',
  };

  isEditing = false;

  toggleEdit(): void {
    this.isEditing = !this.isEditing;
  }

  getRoleLabel(role: string) {
    switch (role) {
      case 'admin':
        return 'Admin';
      case 'editor':
        return 'Éditeur';
      case 'visitor':
        return 'Visiteur';
      default:
        return role;
    }
  }
}
