import { Component, Input, ViewEncapsulation } from '@angular/core';
import { ButtonComponent } from '../button/button.component';
import { SidebarLinkComponent } from './sidebar-link/sidebar-link.component';
import { AuthService } from '../../auth/auth.service';
import { UserService } from '../../sidebar/administration/user.service';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-sidebar',
  imports: [ButtonComponent, CommonModule, SidebarLinkComponent],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss',
  encapsulation: ViewEncapsulation.None,
})
export class SidebarComponent {
  canReadApp$!: Observable<boolean>;
  canReadAdmin$!: Observable<boolean>;
  canReadClasse$!: Observable<boolean>;
  canReadRoles$!: Observable<boolean>;
  canReadCategory$!: Observable<boolean>;
  canReadPerformance$!: Observable<boolean>;
  canReadCorbeille$!: Observable<boolean>;

  constructor(
    private authService: AuthService,
    public userService: UserService
  ) {}

  logout() {
    this.authService.logout();
  }

  ngOnInit(): void {
    this.canReadApp$ = this.userService.canReadService('application');
    this.canReadAdmin$ = this.userService.canReadService('admin');
    this.canReadClasse$ = this.userService.canReadService('classification');
    this.canReadRoles$ = this.userService.canReadService('roles');
    this.canReadCategory$ = this.userService.canReadService('category');
    this.canReadPerformance$ = this.userService.canReadService('performance');
    this.canReadCorbeille$ = this.userService.canReadService('corbeille');
  }
}
