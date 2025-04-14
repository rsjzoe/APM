import { Component } from '@angular/core';
import { CreateRole, Service } from '../../../application/role/role.type';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ServiceDataService } from '../service/service-data.service';
import { RoleService } from '../service/role.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-role-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './role-form.component.html',
  styleUrl: './role-form.component.scss',
})
export class RoleFormComponent {
  services: Service[] = [];
  constructor(
    private serviceDataService: ServiceDataService,
    private createRoleService: RoleService,
    private router: Router
  ) {}

  newRole: CreateRole = {
    roleName: '',
    permissions: [],
  };

  ngOnInit() {
    this.findAllServiceData();
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
    this.createRoleService.add(this.newRole).subscribe({
      next: (response) => {
        this.router.navigate(['/roles']);
      },
      error: (error) => {
        console.error('Error creating role:', error);
      },
    });
  }

  onReset() {
    this.newRole.roleName = '';
    this.initializePermissions();
  }

  findAllServiceData() {
    this.serviceDataService.findAll().subscribe({
      next: (data) => {
        this.services = data;
        this.initializePermissions();
        console.log(this.services);
        console.log(this.newRole);
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des services :', error);
      },
    });
  }
}
