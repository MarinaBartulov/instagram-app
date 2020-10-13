import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { SignUpUser } from '../model/SignUpUser';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private _router: Router, private _authService: AuthService, private _notifier: NotifierService) { }

  signUpUser: SignUpUser;
  showError: boolean;
  messageError: string;

  ngOnInit(): void {

    this.signUpUser = new SignUpUser();
    this.showError = false;
    this.messageError = "";
  }

  onClickedSignUp(){
    console.log(this.signUpUser)
    this._authService.signup(this.signUpUser).subscribe(
      data => {
          this._router.navigate(['/login']);
          this._notifier.notify("success","You have to activate your account by clicking on the link in your email.");
             setTimeout(() => {
          this._notifier.hideAll();
          }, 3000)
      },
      error => {
        this.messageError = error.error;
        console.log(this.messageError)
        this.showError = true;
        setTimeout(() => {
         this.showError = false;
         this.messageError = "";
       }, 5000)

      })
    }
}
