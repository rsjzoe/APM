import { Component } from '@angular/core';
import { CreateRole, Service } from '../../../application/role/role.type';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-role-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './role-form.component.html',
  styleUrl: './role-form.component.scss',
})
export class RoleFormComponent {
  services: Service[] = [
    { id: 1, serviceName: 'Utilisateurs' },
    { id: 2, serviceName: 'Rôles' },
    { id: 3, serviceName: 'Rapports' },
  ];

  newRole: CreateRole = {
    roleName: '',
    permissions: [],
  };

  ngOnInit() {
    this.initializePermissions();
  }

  private initializePermissions() {
    this.newRole.permissions = this.services.map((service) => ({
      serviceId: service.id,
      canCreate: false,
      canRead: false,
      canUpdate: false,
      canDelete: false,
    }));
  }

  onSubmit() {
    console.log('Role to create:', this.newRole);
    // Here you would typically call your API service to save the role
  }

  onReset() {
    this.newRole.roleName = '';
    this.initializePermissions();
  }
}
