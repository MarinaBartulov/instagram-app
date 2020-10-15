import { Component, Input, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Post } from 'src/app/model/Post';
import { PostService } from 'src/app/service/post.service';

@Component({
  selector: 'app-user-profile-posts',
  templateUrl: './user-profile-posts.component.html',
  styleUrls: ['./user-profile-posts.component.css']
})
export class UserProfilePostsComponent implements OnInit {

  constructor(private _postService: PostService) { }

  @Input() posts: Post[];
  showAllPosts: boolean;
  showPostDetails: boolean;
  postId: number;
  currentUser: any;
  newPost: FormData;

  ngOnInit(): void {
    //this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    //this.postId = null;
    //this._postService.getPosts(this.currentUser.id).subscribe(
     // res => {
       // this.posts = res;
        console.log(this.posts);
        this.showPostDetails = false;
        this.showAllPosts = true;
      //}
    //)
    }


  clickedDetails(id:number){

      this.postId = id;
      //this._postService.getPosts(this.currentUser.id).subscribe(
        //res => {
          //this.posts = res;
          //console.log(this.posts);
          this.showAllPosts = false;
          this.showPostDetails = true;
        //}
     // )
      
  }


  cancelPost($event){
    this.showPostDetails = false;
    this.showAllPosts = true;
  }


}
