import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable()
export class NotAuthGuardService implements CanActivate {
  constructor(public auth: AuthService, public router: Router) {}
  canActivate(): boolean {
    if (this.auth.tokenIsPresent()) {
      this.router.navigate(['/home']);
      return false;
    }
    return true;
  }
}