import { Component, Input, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NotifierService } from 'angular-notifier';
import { Post } from '../model/Post';
import { PostDetails } from '../model/PostDetails';
import { NewPostModalComponent } from '../new-post-modal/new-post-modal.component';
import { PostService } from '../service/post.service';

@Component({
  selector: 'app-profile-posts',
  templateUrl: './profile-posts.component.html',
  styleUrls: ['./profile-posts.component.css']
})
export class ProfilePostsComponent implements OnInit {

  constructor(private _postService: PostService, private _modalService: NgbModal, private _notifier: NotifierService) { }

  posts: Post[];
  showAllPosts: boolean;
  showPostDetails: boolean;
  postId: number;
  currentUser: any;
  newPost: FormData;

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

  addNewPost(){
    const modalRef = this._modalService.open(NewPostModalComponent, {size : 'lg'});
    modalRef.result.then((result) => {
      if (result) {
        this.newPost = result;
        console.log(this.newPost)
        this._postService.addNewPost(this.newPost).subscribe(
          res => {
            console.log(res);
          },
          error => {
            this._notifier.notify("error",error.error);
            setTimeout(() => {
              this._notifier.hideAll();
            }, 3000)
          }
        )
      }
      });
  }

  

}
