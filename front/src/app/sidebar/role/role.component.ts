import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Role } from '../../application/role/role.type';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { RoleService } from './service/role.service';
import { ModalConfirmComponent } from '../../components/modal-confirm/modal-confirm.component';
import { combineLatest, map, Observable } from 'rxjs';
import { UserService } from '../administration/user.service';
import { ToastService } from '../../components/toast/service/toast.service';
import { SocketService } from '../../socket.service';

@Component({
  selector: 'app-role',
  imports: [CommonModule, RouterLink, RouterLinkActive, ModalConfirmComponent],
  templateUrl: './role.component.html',
  styleUrl: './role.component.scss',
})
export class RoleComponent {
  roles: Role[] = [];
  roleName: string | null = null;
  canAdd$!: Observable<boolean>;
  canEdit$!: Observable<boolean>;
  canDelete$!: Observable<boolean>;
  canEditOrDelete$!: Observable<boolean>;

  constructor(
    private roleService: RoleService,
    private userService: UserService,
    private toastService: ToastService,
    private socketService: SocketService
  ) {
    this.socketService.onEvent('refetch_role', () => {
      this.init();
    });
  }

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
        this.toastService.success('Rôle supprimé avec succès');
      },
      error: (error) => {
        console.log('erreur de la suppresssion : ' + error);
        this.toastService.error('Erreur lors de la suppression du rôle');
      },
    });
  }

  findAll() {
    this.roleService.findAll().subscribe((roles) => {
      this.roles = roles;
    });
  }

  init() {
    this.canAdd$ = this.userService.canCreateService('roles');
    this.canEdit$ = this.userService.canEditService('roles');
    this.canDelete$ = this.userService.canDeleteService('roles');
    this.canEditOrDelete$ = combineLatest([
      this.canDelete$,
      this.canEdit$,
    ]).pipe(map(([canDelete, canEdit]) => canDelete || canEdit));
    this.findAll();
  }

  ngOnInit() {
    this.init();
  }
}
