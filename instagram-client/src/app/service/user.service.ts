import { ConfigService } from './config.service';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import {HttpHeaders} from '@angular/common/http';

@Injectable()
export class UserService{

    constructor(private _apiService:ApiService, private _config: ConfigService){

    }

    getMyInfo() {
        return this._apiService.get(this._config.currentUser_url)
          .pipe(map(user => {
            localStorage.setItem('currentUser', JSON.stringify(user)); 
            console.log(JSON.parse(localStorage.getItem('currentUser')));
            console.log(user.authorities[0]['authority']);
            return user;
          }));
      }

  
}