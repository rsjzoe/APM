import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { UserService } from '../sidebar/administration/user.service';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
// ito i-evitena anle connecte nefa makao amin'ny login
export class GuestGuard implements CanActivate {
  constructor(
    private userService: UserService,
    private router: Router,
  ) {}

  async canActivate() {
    const currentUrl = window.location.pathname;
    try {
      await firstValueFrom(this.userService.me());
      // raha connecte izy de makao amin'ny home na callbackUrl na currentUrl

      const callbackUrl = new URLSearchParams(window.location.search).get(
        'callbackUrl',
      );
      if (callbackUrl && callbackUrl != '/login') {
        this.router.navigateByUrl(callbackUrl);
      } else if (currentUrl.length > 1 && currentUrl != '/login') {
        this.router.navigateByUrl(currentUrl);
      } else {
        this.router.navigate(['/home']);
      }
      // false satria amzay tsy tafiditra amle composant LoginComposant
      return false;
    } catch (error) {
      // sinon makao amlee amle composant LoginComposant
      return true;
    }
  }
}
