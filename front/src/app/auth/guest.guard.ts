import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { UserService } from '../sidebar/administration/user.service';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
// ito i-evitena anle connecte nefa makao amin'ny login
export class GuestGuard implements CanActivate {
  constructor(private userService: UserService, private router: Router) {}

  async canActivate() {
    try {
      const user = await firstValueFrom(this.userService.me());
      // raha connecte izy de makao amin'ny homee
      this.router.navigate(['/home']);
      // false satria amzay tsy tafiditra amle composant LoginComposant
      return false;
    } catch (error) {
        // sinon makao amlee amle composant LoginComposant
      return true;
    }
  }
}
