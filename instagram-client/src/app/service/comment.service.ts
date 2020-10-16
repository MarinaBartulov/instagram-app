import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { CommentNew } from '../model/CommentNew';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable()
export class CommentService{

    constructor(private _apiService:ApiService, private _config: ConfigService){

    }

    addComment(newComment:CommentNew) {
        const headers = new HttpHeaders({
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          });
        return this._apiService.post(this._config.comment_url,JSON.stringify(newComment), headers)
          .pipe(map(comment => {
            return comment;
          }));
    }

    getCommentsForPost(id:number){
        return this._apiService.get(this._config.comment_url + "/post/" + id)
          .pipe(map(comments => {
            return comments;
          }));
    }

    deleteComment(id:number){
        return this._apiService.delete(this._config.comment_url + "/" + id)
          .pipe(map(comment => {
            return comment;
          }));
    }

    deleteCommentAdmin(id: number){

      return this._apiService.delete(this._config.comment_url + "/" + id + "/admin")
      .pipe(map(comment => {
        return comment;
      }));
    }

    getAllCommentsAdmin(){
      return this._apiService.get(this._config.comment_url + "/admin")
      .pipe(map(comments => {
        return comments;
      }));
    }



  
}