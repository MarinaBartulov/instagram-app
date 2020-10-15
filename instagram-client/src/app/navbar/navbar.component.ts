import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private _router: Router, private _authService:AuthService) { }

  currentUser: any;
  role: string;
  ngOnInit(): void {
     this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
     this.role = localStorage.getItem("role");
  }

  goToAdminPage(){

    this._router.navigate(["/adminPage"]);

  }

  goToSearchUsers(){
    this._router.navigate(["/searchUsers"]);
  }

  goToProfile(){
    this._router.navigate(["/profile"]);
  }

  logout(){
     this._authService.logout();
  }

}
