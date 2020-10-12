import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SignUpUser } from '../model/SignUpUser';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private _router: Router, private _authService: AuthService) { }

  signUpUser: SignUpUser;
  showError: boolean;

  ngOnInit(): void {

    this.signUpUser = new SignUpUser();
    this.showError = false;
  }

  onClickedSignUp(){
    this._authService.signup(this.signUpUser).subscribe(
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
