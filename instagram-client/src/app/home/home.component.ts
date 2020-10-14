import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PostDetails } from '../model/PostDetails';
import { AuthService } from '../service/auth.service';
import { PostService } from '../service/post.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(private _router: Router, private _authService: AuthService, private _postService: PostService) {}

  postsDetails: PostDetails[];
  currentUser: any;

  ngOnInit(): void {

    this._postService.getPostsFeed().subscribe(
      res => {
        this.postsDetails = res;
        console.log(this.postsDetails);
      })
   }

  logout() {
    this._authService.logout();
  }

  goToProfile() {
    this._router.navigate(['/profile']);
  }

  goToSearchUsers(){
    this._router.navigate(['/searchUsers']);
  }
}
