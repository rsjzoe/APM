import { Component } from '@angular/core';
import {
  CreatePermission,
  CreateRole,
  Service,
} from '../../../application/role/role.type';
import { Router } from '@angular/router';
import { ToastService } from '../../../components/toast/service/toast.service';
import { RoleService } from '../service/role.service';
import { ServiceDataService } from '../service/service-data.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-role',
  imports: [CommonModule, FormsModule],
  templateUrl: './add-role.component.html',
  styleUrl: './add-role.component.scss',
})
export class AddRoleComponent {
  services: Service[] = [];
  newRole: CreateRole = {
    roleName: '',
    permissions: [],
  };

  constructor(
    private serviceDataService: ServiceDataService,
    private createRoleService: RoleService,
    private router: Router,
    private toastService: ToastService
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
        this.toastService.success('Rôle ajouté avec succès');
      },
      error: (err) => {
        this.toastService.error("Erreur lors de l'ajout du rôle");
        console.error(err);
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
