import { Component, OnInit } from '@angular/core';
import { NotifierService } from 'angular-notifier';
import { CommentAdmin } from '../model/CommentAdmin';
import { UserAdmin } from '../model/UserAdmin';
import { CommentService } from '../service/comment.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  constructor(private _commentService: CommentService, private _notifier: NotifierService, private _userService: UserService) { }

  showUsers: boolean;
  showComments: boolean;
  comments: CommentAdmin[];
  users: UserAdmin[];
  currentUser: any;

  ngOnInit(): void {
    this.showUsers = false;
    this.showComments = false;
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
  }

  showDivComments(){

    this.getAllComments();
  }

  showDivUsers(){
    this.getAllUsers();
  }

  delete(id:number){
    this._commentService.deleteCommentAdmin(id).subscribe(
      res => {
        console.log(res)
        this._notifier.notify("success","You have successfully deleted the comment.");
             setTimeout(() => {
          this._notifier.hideAll();
          }, 3000)
        this.getAllComments();
      },
      error => {
        this._notifier.notify("error",error.error);
        setTimeout(() => {
          this._notifier.hideAll();
        }, 3000)
      }
    )

  }

  getAllComments(){

    this._commentService.getAllCommentsAdmin().subscribe(
      res => {
          this.comments = res;
          this.showComments = true;
          this.showUsers = false;
      },
      error => {
        this._notifier.notify("error",error.error);
        setTimeout(() => {
          this._notifier.hideAll();
        }, 3000)
      }
    )

  }

  banUser(id:number){
     this._userService.banUser(id).subscribe(
       res => {
         console.log(res);
         this.getAllUsers();
         this._notifier.notify("success","You have successfully changed the user's status.");
             setTimeout(() => {
          this._notifier.hideAll();
          }, 3000)
       },
       error => {
        this._notifier.notify("error",error.error);
        setTimeout(() => {
          this._notifier.hideAll();
        }, 3000)
       }
     )
  }

  getAllUsers(){
    this._userService.getAllUsers().subscribe(
      res => {
        this.users = res;
        this.showComments = false;
        this.showUsers = true;
      },
      error => {
        this._notifier.notify("error",error.error);
        setTimeout(() => {
          this._notifier.hideAll();
        }, 3000)
      }
    )
  }



}
