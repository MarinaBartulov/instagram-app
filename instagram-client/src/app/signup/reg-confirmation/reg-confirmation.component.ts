import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../service/auth.service';


@Component({
  selector: 'app-reg-confirmation',
  templateUrl: './reg-confirmation.component.html',
  styleUrls: ['./reg-confirmation.component.css']
})
export class RegConfirmationComponent implements OnInit {

  constructor(private _route: ActivatedRoute, private _authService: AuthService, private _router: Router) {
  }

  success: boolean;
  error: boolean;
  token: String;
  errorMessage: String = "";

  ngOnInit() {
    this._route.paramMap.subscribe(params => { 
    this.token = params.get('token');
    this._authService.confirmRegistration(this.token).subscribe(
      res => {
            this.success = true;
            this.error = false;
      },
      error => {
            console.log(error);
            this.error = true;
            this.success = false;
            this.errorMessage = error.error;
      }
    ) 
  })
  }

goToLogin(){
 this._router.navigate(['\login']);
}

}
