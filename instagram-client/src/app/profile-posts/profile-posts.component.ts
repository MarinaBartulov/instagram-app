import { Component, Input, OnInit } from '@angular/core';
import { Post } from '../model/Post';
import { PostDetails } from '../model/PostDetails';
import { PostService } from '../service/post.service';

@Component({
  selector: 'app-profile-posts',
  templateUrl: './profile-posts.component.html',
  styleUrls: ['./profile-posts.component.css']
})
export class ProfilePostsComponent implements OnInit {

  constructor(private _postService: PostService) { }

  posts: Post[];
  showAllPosts: boolean;
  showPostDetails: boolean;
  postId: number;
  currentUser: any;

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    this.postId = null;
    this._postService.getPosts(this.currentUser.id).subscribe(
      res => {
        this.posts = res;
        console.log(this.posts);
        this.showPostDetails = false;
        this.showAllPosts = true;
      }
    )
    }


  clickedDetails(id:number){

      this.postId = id;
      this._postService.getPosts(this.currentUser.id).subscribe(
        res => {
          this.posts = res;
          console.log(this.posts);
          this.showAllPosts = false;
          this.showPostDetails = true;
        }
      )
      
  }

  deletePost($event){
    this._postService.getPosts(this.currentUser.id).subscribe(
      res => {
        this.posts = res;
        console.log(this.posts);
        this.showPostDetails = false;
        this.showAllPosts = true;
      }
    )
  }

  cancelPost($event){
    this.showPostDetails = false;
    this.showAllPosts = true;
  }

  

}
