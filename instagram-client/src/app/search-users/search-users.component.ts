import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { UserService } from '../service/user.service';
import { User } from '../model/user';

@Component({
  selector: 'app-search-users',
  templateUrl: './search-users.component.html',
  styleUrls: ['./search-users.component.css']
})
export class SearchUsersComponent implements OnInit {

  constructor(private _router: Router, private _authService: AuthService,
    private _userService: UserService) { }

  username: string; 
  foundUsers: User; 

  ngOnInit(): void {
  }

  logout(){
    this._authService.logout();
  }

  goToProfile(){
    this._router.navigate(['/profile']);
  }

  goToSearchUsers(){
    this._router.navigate(['/searchUsers']);
  }

  searchUsers(){

  }
  
  follow(id:number){

  }


}
