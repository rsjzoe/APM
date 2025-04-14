import { Component } from '@angular/core';
import { CreateRole, Service } from '../../../application/role/role.type';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ServiceDataService } from '../service/service-data.service';

@Component({
  selector: 'app-role-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './role-form.component.html',
  styleUrl: './role-form.component.scss',
})
export class RoleFormComponent {
  services: Service[] = [];
  constructor(private serviceDataService: ServiceDataService) {}

  newRole: CreateRole = {
    roleName: '',
    permissions: [],
  };

  ngOnInit() {
    this.findAllServiceData();
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

  findAllServiceData() {
    this.serviceDataService.findAll().subscribe({
      next: (data) => {
        console.log(data);
        this.services = data;
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des services :', error);
      },
    });
  }
}
