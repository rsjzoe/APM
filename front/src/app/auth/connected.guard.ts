import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { UserService } from '../sidebar/administration/user.service';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
// ito i-evitena anle mbola tsy connecte nefa makao amin'ny /home ohatra
export class ConnectedGuard implements CanActivate {
  constructor(
    private userService: UserService,
    private router: Router,
  ) {}

  async canActivate() {
    const currentUrl = window.location.pathname;

    try {
      await firstValueFrom(this.userService.me());
      if (currentUrl == '/') {
        this.router.navigate(['/home']).then(() => {
          window.location.replace('/home');
        });

        return false;
      }
      return true;
    } catch (error) {
      this.router.navigate(['/login'], {
        queryParams: { callbackUrl: currentUrl },
      });
      return false;
    }
  }
}
