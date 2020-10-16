import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { SearchUsersComponent } from './search-users/search-users.component';
import { RegConfirmationComponent } from './signup/reg-confirmation/reg-confirmation.component';
import { SignupComponent } from './signup/signup.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { AuthGuardService as AuthGuard } from './service/auth-guard.service';
import { RoleGuardService as RoleGuard } from './service/role-guard.service';
import { NotAuthGuardService as NotAuthGuard } from './service/not-auth-guard.service';
import { NotAuthenticatedComponent } from './not-authenticated/not-authenticated.component';
import { NotAuthorizedComponent } from './not-authorized/not-authorized.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent, canActivate: [NotAuthGuard]},
  {path: 'signup', component: SignupComponent, canActivate: [NotAuthGuard]},
  {path: 'confirmationRegistration/:token', component: RegConfirmationComponent},
  {path: 'searchUsers', component: SearchUsersComponent, canActivate: [AuthGuard]},
  {path: 'userProfile/:id', component: UserProfileComponent, canActivate: [AuthGuard]},
  {path: 'adminPage', component: AdminPageComponent,  canActivate: [RoleGuard]},
  {path: 'notAuthenticated', component: NotAuthenticatedComponent},
  {path: 'notAuthorized', component: NotAuthorizedComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
