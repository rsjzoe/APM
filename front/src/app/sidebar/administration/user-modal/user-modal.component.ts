import { CommonModule } from '@angular/common';
import { Component, Input, SimpleChanges } from '@angular/core';
import { Role, User } from '../user.type';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-modal',
  imports: [CommonModule, FormsModule],
  templateUrl: './user-modal.component.html',
  styleUrl: './user-modal.component.scss',
})
export class UserModalComponent {
  @Input() isEditing: number | null = null;
  newUserName = '';
  newUserEmail = '';
  @Input() userEditing: User | null = null;
  newUserRole: Role = 'user';
  @Input() addUser = (name: string, email: string, role: Role) => {};
  @Input() updateUser = (name: string, email: string, role: Role) => {};

  add() {
    if (this.newUserName.length == 0 || this.newUserEmail.length == 0) return;
    this.addUser(this.newUserName, this.newUserEmail, this.newUserRole);
    this.newUserName = '';
    this.newUserEmail = '';
    this.newUserRole = 'user';
  }

  update() {
    if (this.newUserName.length == 0 || this.newUserEmail.length == 0) return;
    this.updateUser(this.newUserName, this.newUserEmail, this.newUserRole);
    this.newUserName = '';
    this.newUserEmail = '';
    this.newUserRole = 'user';
  }

  ngOnChanges(changes: SimpleChanges) {
    console.log(changes);
    const currentUserEditing: User | null = changes['userEditing'].currentValue;
    if (currentUserEditing == null) {
      return;
    }
    this.newUserName = currentUserEditing.name;
    this.newUserEmail = currentUserEditing.email;
    this.newUserRole = currentUserEditing.role;
  }
}
