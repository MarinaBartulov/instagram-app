import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { User } from '../model/User';
import { UserProfileDetails } from '../model/UserProfileDetails';
import { AuthService } from '../service/auth.service';
import { FollowService } from '../service/follow.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  constructor(private _router: Router, private _route: ActivatedRoute, private _userService: UserService,
     private _notifier: NotifierService, private _authService:AuthService, private _followService: FollowService) { }

  userId: number;
  userProfileDetails: UserProfileDetails;
  showDivPosts: boolean;
  showDivFollowers: boolean;
  showDivFollowing: boolean;
  followers: User[];
  following: User[];


  ngOnInit(): void {
    this.userProfileDetails = new UserProfileDetails();
    this._route.paramMap.subscribe(params => {
      this.userId = parseInt(params.get('id'));
      console.log("User id: " + this.userId)
    });
    
    this.getUserProfileDetails();
    this.showDivPosts = true;
    this.showDivFollowers = false;
    this.showDivFollowing = false;
  
  }

  getUserProfileDetails(){
    this._userService.getUserProfileDetails(this.userId).subscribe(
      res => {
        this.userProfileDetails = res;
        console.log(this.userProfileDetails)
      },
      error => {
        this._notifier.notify("error",error.error);
          setTimeout(() => {
        this._notifier.hideAll();
        }, 3000)
          }
    )
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

  showPosts(){

      this.showDivPosts = true;
      this.showDivFollowers = false;
      this.showDivFollowing = false;
       
  }

  showFollowers(){
    this._userService.getFollowers(this.userId).subscribe(
      res => {
        this.followers = res;
        console.log(this.followers);
        this.showDivPosts = false;
        this.showDivFollowers = true;
        this.showDivFollowing = false;
      }
    )  
  }


  showFollowing(){

    this._userService.getFollowing(this.userId).subscribe(
      res => {
        this.following = res;
        console.log(this.following);
        this.showDivPosts = false;
        this.showDivFollowers = false;
        this.showDivFollowing = true;
      }
    )  
  }

  follow(){
    if(this.userProfileDetails.user.follow){
      this._followService.unfollowUser(this.userProfileDetails.user.id).subscribe(
        res => {
          console.log(res);
          this.userProfileDetails.user.follow = false;
          
        }
      )
    }else{
      this._followService.followUser(this.userProfileDetails.user.id).subscribe(
        res => {
          console.log(res);
          this.userProfileDetails.user.follow = true;
        }
      )
    }
  }

}
