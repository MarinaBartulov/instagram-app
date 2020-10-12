import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginUser } from '../model/LoginUser';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private _router: Router, private _authService: AuthService) { }

  loginUser: LoginUser;
  showError: boolean;

  ngOnInit(): void {
    this.loginUser = new LoginUser();
    this.showError = false;
  }

  onClickedLogin(){
      console.log(this.loginUser)
      this._authService.login(this.loginUser).subscribe(
        data => {
            this._router.navigate(['/home']);
        },
        error => {
          this.showError = true;
          setTimeout(() => {
           this.showError = false;
         }, 5000)
  
        })
  }

}
