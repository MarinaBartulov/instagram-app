import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable()
export class RoleGuardService implements CanActivate {
  constructor(public auth: AuthService, public router: Router) {}
  canActivate(): boolean {
    if (!this.auth.tokenIsPresent()) {
         this.router.navigate(['/notAuthenticated']);
         return false;
    }else{
        let role = localStorage.getItem("role");
        if(role !== 'ROLE_ADMIN'){
            this.router.navigate(['/notAuthorized']);
            return false;
        }
    }
    return true;
  }
}