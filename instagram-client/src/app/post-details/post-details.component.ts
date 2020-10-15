import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LikesModalComponent } from '../likes-modal/likes-modal.component';
import { CommentNew } from '../model/CommentNew';
import { PostDetails } from '../model/PostDetails';
import { CommentService } from '../service/comment.service';
import { LikeService } from '../service/like.service';
import { PostService } from '../service/post.service';

@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.css']
})
export class PostDetailsComponent implements OnInit {

  constructor(private _commentService: CommentService, private _postService: PostService,
     private _likeService: LikeService, private _modalService: NgbModal) { }

  postDetails: PostDetails = new PostDetails();
  showComments: boolean;
  newComment: CommentNew;
  @Input() postId: number;
  currentUser: any;
  @Output() deletePostEvent =  new EventEmitter<boolean>();
  @Output() cancelPostEvent =  new EventEmitter<boolean>();


  ngOnInit(): void {

    this.getPostDetails(this.postId);
    this.showComments = false;
    this.newComment = new CommentNew();
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
  }

  showCommentsF($event){
    $event.preventDefault();
    $event.stopPropagation();
    this.showComments = !this.showComments;
  }

  getPostDetails(id:number){

    this._postService.getPostDetails(this.postId).subscribe(
      res => {
        this.postDetails = res;
        console.log(this.postDetails);

      }
    )
  }

  writeComment(id: number){

    this.newComment.postId = id;
    this._commentService.addComment(this.newComment).subscribe(
      res => {
        console.log(res);
        this.getPostDetails(this.postId);
        this.newComment.text = "";
      }
    ) 
  }

  deleteComment($event, id: number){

     $event.preventDefault();
     $event.stopPropagation();
     this._commentService.deleteComment(id).subscribe(
       res => {
         console.log(res);
         this.getPostDetails(this.postId);
       }
     )
  }

  likePost(id: number){
      
       if(this.postDetails.liked){
         this._likeService.unlikePost(id).subscribe(
           res => {
             console.log(res);
             this.getPostDetails(this.postId);
           }
         )
       }else{
         this._likeService.likePost(id).subscribe(
           res => {
             console.log();
             this.getPostDetails(this.postId);
           }
         )
       }
  }

  deletePost($event, id: number){
    $event.preventDefault();
    $event.stopPropagation();
    this._postService.deletePost(id).subscribe(
      res => {
          console.log(res);
          this.deletePostEvent.emit(true);
      }
    )
  }

  cancel(){
     this.cancelPostEvent.emit(true);
  }

  clickLikes($event){
    $event.preventDefault();
    $event.stopPropagation();
    const modalRef = this._modalService.open(LikesModalComponent, {size : 'sm', scrollable: true});
    modalRef.componentInstance.likes = this.postDetails.likes;
  }

}
