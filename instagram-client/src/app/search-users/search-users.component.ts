import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { UserService } from '../service/user.service';
import { UserFollow } from '../model/UserFollow';
import { FollowService } from '../service/follow.service';

@Component({
  selector: 'app-search-users',
  templateUrl: './search-users.component.html',
  styleUrls: ['./search-users.component.css']
})
export class SearchUsersComponent implements OnInit {

  constructor(private _router: Router, private _authService: AuthService,
    private _userService: UserService, private _followService: FollowService) { }

  username: string; 
  foundUsers: UserFollow; 

  ngOnInit(): void {
  }


  searchUsers(){
    this._userService.searchUsers(this.username).subscribe(
      res => {
        this.foundUsers = res;
        console.log(this.foundUsers)
      }
    )
  }
  
  follow(id:number, follow: boolean){

    if(follow){
       this._followService.unfollowUser(id).subscribe(
         res => {
           console.log(res)
           this.searchUsers();
         }
       )
    }else{
        this._followService.followUser(id).subscribe(
          res => {
            console.log(res);
            this.searchUsers();
          }
        )
    }

  }

  goOnProfile(id:number){
    console.log("rado")
    this._router.navigate(['/userProfile', id]);
  }


}
