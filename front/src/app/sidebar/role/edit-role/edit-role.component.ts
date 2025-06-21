import { Component } from '@angular/core';
import { RoleFormComponent } from '../role-form/role-form.component';
import { ActivatedRoute, Router } from '@angular/router';
import { RoleService } from '../service/role.service';
import {
  Permission,
  Role,
  Service,
  UpdatePermission,
  UpdateRole,
} from '../../../application/role/role.type';
import { ServiceDataService } from '../service/service-data.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ToastService } from '../../../components/toast/service/toast.service';

@Component({
  selector: 'app-edit-role',
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-role.component.html',
  styleUrl: './edit-role.component.scss',
})
export class EditRoleComponent {
  roleName: string | null = null;
  role: Role | null = null;
  services: Service[] = [];

  updateRole: UpdateRole = {
    permissions: [],
  };

  constructor(
    private serviceDataService: ServiceDataService,
    private activatedRoute: ActivatedRoute,
    private roleService: RoleService,
    private router: Router,
    private toastService: ToastService
  ) {}

  private initializePermissions() {
    this.updateRole.permissions = this.services.map((service) => ({
      serviceId: service.id,
      canCreate: false,
      canRead: false,
      canUpdate: false,
      canDelete: false,
    }));
    this.findRoleByName(this.roleName!);
  }

  findRoleByName(name: string) {
    this.roleService.findByName(name).subscribe((role) => {
      this.role = role;
      this.role.permissions.forEach((permission) => {
        const service = this.updateRole.permissions.find(
          (p) => p.serviceId === permission.service.id
        );
        if (service) {
          service.canCreate = permission.canCreate;
          service.canRead = permission.canRead;
          service.canUpdate = permission.canUpdate;
          service.canDelete = permission.canDelete;
        }
      });
    });
  }

  findAllServiceData() {
    this.serviceDataService.findAll().subscribe({
      next: (data) => {
        this.services = data;
        this.initializePermissions();
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des services :', error);
      },
    });
  }

  isAllSelected(permission: UpdatePermission): boolean {
    return (
      permission.canCreate &&
      permission.canRead &&
      permission.canUpdate &&
      permission.canDelete
    );
  }

  toggleAllPermissions(permission: UpdatePermission, event: Event): void {
    const input = event.target as HTMLInputElement;
    const isChecked = input.checked;
    permission.canCreate = isChecked;
    permission.canRead = isChecked;
    permission.canUpdate = isChecked;
    permission.canDelete = isChecked;
  }

  onSubmit() {
    console.log('Role to create:', this.updateRole);
    if (!this.role) return;
    this.roleService.update(this.role.id, this.updateRole).subscribe({
      next: (response) => {
        this.router.navigate(['/roles']);
        this.roleName = null;
        this.toastService.success('Rôle modifié avec succès');
      },
      error: (error) => {
        console.log('erreur de la modification : ' + error);
        this.toastService.error('Erreur lors de la modification du rôle');
      },
    });
  }

  onReset() {
    this.initializePermissions();
  }

  ngOnInit() {
    this.roleName = this.activatedRoute.snapshot.paramMap.get('name');
    this.findAllServiceData();
  }
}
