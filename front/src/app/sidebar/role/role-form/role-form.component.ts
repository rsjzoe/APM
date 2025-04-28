import { Component, Input } from '@angular/core';
import {
  CreatePermission,
  CreateRole,
  Role,
  Service,
} from '../../../application/role/role.type';
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
  @Input() role: Role | null = null;

  newRole: CreateRole = {
    roleName: '',
    permissions: [],
  };

  constructor(
    private serviceDataService: ServiceDataService,
    private createRoleService: RoleService,
    private router: Router
  ) {}

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

  isAllSelected(permission: CreatePermission): boolean {
    return (
      permission.canCreate &&
      permission.canRead &&
      permission.canUpdate &&
      permission.canDelete
    );
  }

  toggleAllPermissions(permission: CreatePermission, event: Event): void {
    const input = event.target as HTMLInputElement;
    const isChecked = input.checked;
    permission.canCreate = isChecked;
    permission.canRead = isChecked;
    permission.canUpdate = isChecked;
    permission.canDelete = isChecked;
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
