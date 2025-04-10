import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Role } from '../../application/role/role.type';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { RoleService } from './service/role.service';

@Component({
  selector: 'app-role',
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './role.component.html',
  styleUrl: './role.component.scss',
})
export class RoleComponent {
  roles: Role[] = [];

  constructor(private roleService: RoleService) {}

  findAll() {
    this.roleService.findAll().subscribe((roles) => {
      this.roles = roles;
    });
  }

  ngOnInit() {
    this.findAll();
  }
}
