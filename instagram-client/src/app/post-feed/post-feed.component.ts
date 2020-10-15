import { Component, Input, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LikesModalComponent } from '../likes-modal/likes-modal.component';
import { CommentNew } from '../model/CommentNew';
import { PostDetails } from '../model/PostDetails';
import { CommentService } from '../service/comment.service';
import { LikeService } from '../service/like.service';
import { PostService } from '../service/post.service';

@Component({
  selector: 'app-post-feed',
  templateUrl: './post-feed.component.html',
  styleUrls: ['./post-feed.component.css']
})
export class PostFeedComponent implements OnInit {

  constructor(private _postService: PostService, private _commentService: CommentService,
     private _likeService: LikeService, private _modalService: NgbModal) { }

  newComment: CommentNew;
  @Input() postDetails: PostDetails;
  showComments: boolean;
  currentUser: any;


  ngOnInit(): void {
      this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
      this.newComment = new CommentNew();
      this.showComments = false;

  }

  showCommentsF($event){
    $event.preventDefault();
    $event.stopPropagation();
    this.showComments = !this.showComments;
  }

  writeComment(id){

    this.newComment.postId = id;
    this._commentService.addComment(this.newComment).subscribe(
      res => {
        console.log(res);
        this.getPostDetails(this.postDetails.id);
        this.newComment.text = "";
      }
    ) 

  }

  likePost(id){
    if(this.postDetails.liked){
      this._likeService.unlikePost(id).subscribe(
        res => {
          console.log(res);
          this.getPostDetails(this.postDetails.id);
        }
      )
    }else{
      this._likeService.likePost(id).subscribe(
        res => {
          console.log();
          this.getPostDetails(this.postDetails.id);
        }
      )
    }
  }

  deleteComment($event, id){
    $event.preventDefault();
     $event.stopPropagation();
     this._commentService.deleteComment(id).subscribe(
       res => {
         console.log(res);
         this.getPostDetails(this.postDetails.id);
       }
     )
  }

  getPostDetails(id:number){

    this._postService.getPostDetails(this.postDetails.id).subscribe(
      res => {
        this.postDetails = res;
        console.log(this.postDetails);

      }
    )
  }

  clickLikes($event){
    $event.preventDefault();
    $event.stopPropagation();
    const modalRef = this._modalService.open(LikesModalComponent, {size : 'sm', scrollable: true});
    modalRef.componentInstance.likes = this.postDetails.likes;
  }


}
