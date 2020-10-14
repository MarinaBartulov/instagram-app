import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable()
export class FollowService{

    constructor(private _apiService:ApiService, private _config: ConfigService){

    }

    followUser(id:number) {
        const headers = new HttpHeaders({
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          });
        return this._apiService.post(this._config.follow_url + "/user/" + id, headers)
          .pipe(map(follow => {
            return follow;
          }));
      }

    unfollowUser(id:number) {
    return this._apiService.delete(this._config.follow_url + "/user/" + id)
        .pipe(map(follow => {
        return follow;
        }));
    }

  
}