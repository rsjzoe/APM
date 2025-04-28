import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Role } from '../../application/role/role.type';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { RoleService } from './service/role.service';
import { ModalConfirmComponent } from '../../components/modal-confirm/modal-confirm.component';

@Component({
  selector: 'app-role',
  imports: [CommonModule, RouterLink, RouterLinkActive, ModalConfirmComponent],
  templateUrl: './role.component.html',
  styleUrl: './role.component.scss',
})
export class RoleComponent {
  roles: Role[] = [];
  roleName: string | null = null;

  constructor(private roleService: RoleService) {}

  saveRoleName = (roleName: string) => {
    this.roleName = roleName;
  };

  onConfirmDelete = () => {
    if (this.roleName) {
      this.deleteRole(this.roleName);
    }
  };

  deleteRole(name: string) {
    this.roleService.deleteByName(name).subscribe({
      next: () => {
        this.roles = this.roles.filter((role) => role.roleName !== name);
      },
    });
  }

  findAll() {
    this.roleService.findAll().subscribe((roles) => {
      this.roles = roles;
    });
  }

  ngOnInit() {
    this.findAll();
  }
}
