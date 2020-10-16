import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginUser } from '../model/LoginUser';
import { AuthService } from '../service/auth.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private _router: Router, private _authService: AuthService, private _userService: UserService) { }

  loginUser: LoginUser;
  showError: boolean;
  errorMsg: string;

  ngOnInit(): void {
    this.loginUser = new LoginUser();
    this.showError = false;
    this.errorMsg = "";
  }

  onClickedLogin(){
      console.log(this.loginUser)
      this._authService.login(this.loginUser).subscribe(
        data => {
          this._userService.getMyInfo().subscribe(user => {
              this._router.navigate(['/home']);
          });
           
        },
        error => {
          this.showError = true;
          this.errorMsg = error.error;
          setTimeout(() => {
           this.showError = false;
         }, 5000)
  
        })
  }

}
