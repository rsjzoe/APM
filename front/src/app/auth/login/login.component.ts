import { Component } from '@angular/core';
import { ButtonComponent } from '../../components/button/button.component';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  trigramme = '';
  password = '';
  errorMessage: string | null = null;

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    this.authService.login(this.trigramme, this.password).subscribe({
      next: () => {
        const callbackUrl = new URLSearchParams(window.location.search).get(
          'callbackUrl'
        );
        const nextUrl = callbackUrl || '/home';

        this.router.navigate([nextUrl]).then(() => {
          window.location.replace(nextUrl);
        });
      },
      error: (err) => {
        this.errorMessage = "Nom d'utilisateur ou mot de passe incorrect";
      },
    });
  }
}
