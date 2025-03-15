import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
} from '@angular/router';

import { UserService } from '../sidebar/administration/user.service';
import { Role } from '../sidebar/administration/user.type';

@Injectable({
  providedIn: 'root',
})

export class RoleGuard implements CanActivate {
  constructor(private userService: UserService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    const user = this.userService.getUserConnected();
    if (!user) {
      this.router.navigate(['/login']);
      return false;
    }

    const requiredRoles: Role[] = route.data['roles'];
    if (!requiredRoles || requiredRoles.includes(user.role)) {
      return true;
    }

    // Redirection si l'utilisateur n'a pas le bon rôle
    this.router.navigate(['/']);
    return false;
  }
}
