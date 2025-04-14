import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
} from '@angular/router';

import { UserService } from '../sidebar/administration/user.service';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RoleGuard implements CanActivate {
  constructor(private userService: UserService, private router: Router) {}

  async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const user = this.userService.getUserConnected();
    if (!user) {
      this.router.navigate(['/login']);
      return false;
    }

    const serviceName = route.data['serviceName'];
    const action = route.data['action'];

    if (serviceName && action) {
      const hasAccess = await firstValueFrom(
        this.userService.hasAccess(serviceName, action)
      );
      if (hasAccess) {
        return true;
      } else {
        this.router.navigate(['/life-cycle']);
        return false;
      }
    }

    return true;
  }
}
