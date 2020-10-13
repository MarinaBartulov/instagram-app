import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private _router: Router, private _authService: AuthService) { }

  currentUser: any;

  ngOnInit(): void {
    this.currentUser = localStorage.getItem("currentUser");
    console.log(this.currentUser)
  }

  

  logout(){
    this._authService.logout();
  }

  goToProfile(){
    this._router.navigate(['/profile']);
  }

}
