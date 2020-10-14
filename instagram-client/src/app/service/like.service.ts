import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable()
export class LikeService{

    constructor(private _apiService:ApiService, private _config: ConfigService){

    }

    likePost(id:number) {
        const headers = new HttpHeaders({
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          });
        return this._apiService.post(this._config.like_url + "/post/" + id, headers)
          .pipe(map(like => {
            return like;
          }));
      }

    unlikePost(id:number) {
    return this._apiService.delete(this._config.like_url + "/post/" + id)
        .pipe(map(like => {
        return like;
        }));
    }

  
}