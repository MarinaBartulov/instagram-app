import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from '../model/Post';
import { User } from '../model/User';
import { AuthService } from '../service/auth.service';
import { FollowService } from '../service/follow.service';
import { PostService } from '../service/post.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private _router: Router, private _authService: AuthService, private _postService: PostService,
     private _userService: UserService, private _followService: FollowService) { }

  currentUser: any;
  showDivPosts: boolean;
  showDivFollowers: boolean;
  showDivFollowing: boolean;
  posts: Post[] = [];
  followers: User[];
  following: User[];

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    console.log(this.currentUser)
    
    this.showDivPosts = true;
    this.showDivFollowers = false;
    this.showDivFollowing = false;
    
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
    this._userService.getFollowers(this.currentUser.id).subscribe(
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

    this._userService.getFollowing(this.currentUser.id).subscribe(
      res => {
        this.following = res;
        console.log(this.following);
        this.showDivPosts = false;
        this.showDivFollowers = false;
        this.showDivFollowing = true;
      }
    )  

  }

  unfollow(id){

    this._followService.unfollowUser(id).subscribe(
      res => {
        console.log(res);
        this.showFollowing();
      }
    )

  }

}
