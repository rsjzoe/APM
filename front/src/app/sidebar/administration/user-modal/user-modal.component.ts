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
  newUserTrigramme = '';
  @Input() userEditing: User | null = null;
  newUserRole: Role = 'user';
  @Input() addUser = (name: string, trigramme: string, role: Role) => {};
  @Input() updateUser = (name: string, trigramme: string, role: Role) => {};

  add() {
    if (this.newUserName.length == 0 || this.newUserTrigramme.length == 0)
      return;
    this.addUser(this.newUserName, this.newUserTrigramme, this.newUserRole);
    this.newUserName = '';
    this.newUserTrigramme = '';
    this.newUserRole = 'user';
  }

  update() {
    if (this.newUserName.length == 0 || this.newUserTrigramme.length == 0)
      return;
    this.updateUser(this.newUserName, this.newUserTrigramme, this.newUserRole);
    this.newUserName = '';
    this.newUserTrigramme = '';
    this.newUserRole = 'user';
  }

  ngOnChanges(changes: SimpleChanges) {
    console.log(changes);
    const currentUserEditing: User | null = changes['userEditing'].currentValue;
    if (currentUserEditing == null) {
      return;
    }
    this.newUserName = currentUserEditing.name;
    this.newUserTrigramme = currentUserEditing.trigramme;
    this.newUserRole = currentUserEditing.role;
  }
}
