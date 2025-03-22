import { Component, Input, ViewEncapsulation } from '@angular/core';
import { ButtonComponent } from '../button/button.component';
import { SidebarLinkComponent } from './sidebar-link/sidebar-link.component';
import { AuthService } from '../../auth/auth.service';
import { UserService } from '../../sidebar/administration/user.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sidebar',
  imports: [ButtonComponent, CommonModule, SidebarLinkComponent],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss',
  encapsulation: ViewEncapsulation.None,
})
export class SidebarComponent {
  constructor(
    private authService: AuthService,
    public userService: UserService
  ) {}

  logout() {
    this.authService.logout();
  }
}
