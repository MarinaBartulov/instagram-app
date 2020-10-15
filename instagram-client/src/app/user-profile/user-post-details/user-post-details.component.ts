import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LikesModalComponent } from 'src/app/likes-modal/likes-modal.component';
import { CommentNew } from 'src/app/model/CommentNew';
import { PostDetails } from 'src/app/model/PostDetails';
import { CommentService } from 'src/app/service/comment.service';
import { LikeService } from 'src/app/service/like.service';
import { PostService } from 'src/app/service/post.service';

@Component({
  selector: 'app-user-post-details',
  templateUrl: './user-post-details.component.html',
  styleUrls: ['./user-post-details.component.css']
})
export class UserPostDetailsComponent implements OnInit {

  constructor(private _commentService: CommentService, private _postService: PostService,
    private _likeService: LikeService, private _modalService: NgbModal) { }

  postDetails: PostDetails = new PostDetails();
  showComments: boolean;
  newComment: CommentNew;
  @Input() postId: number;
  currentUser: any;
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
