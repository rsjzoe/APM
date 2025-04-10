import { Component } from '@angular/core';
import { RoleFormComponent } from '../role-form/role-form.component';

@Component({
  selector: 'app-add-role',
  imports: [RoleFormComponent],
  templateUrl: './add-role.component.html',
  styleUrl: './add-role.component.scss',
})
export class AddRoleComponent {}
