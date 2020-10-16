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
            localStorage.setItem('role', user.authorities[0]['authority']);
            console.log(JSON.parse(localStorage.getItem('currentUser')));
            console.log(user.authorities[0]['authority']);
            return user;
          }));
      }

    getFollowers(id:number){
      return this._apiService.get(this._config.user_url + "/" + id + "/followers")
      .pipe(map(followers => {
        return followers;
      }));
  } 

    getFollowing(id:number){
      return this._apiService.get(this._config.user_url + "/" + id + "/following")
      .pipe(map(following => {
        return following;
      }));
    }

    searchUsers(username: string){
      return this._apiService.get(this._config.user_url + "/search?username=" + username)
      .pipe(map(usersFound => {
        return usersFound;
      }));
    }

    getUserProfileDetails(id: number){
      return this._apiService.get(this._config.user_url + "/" + id + "/profileDetails")
      .pipe(map(userProfileDetails => {
        return userProfileDetails;
      }));
    }

    banUser(id: number){
      const headers = new HttpHeaders({
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      });
      return this._apiService.put(this._config.user_url + "/" + id + "/ban", headers)
      .pipe(map(user => {
        return user;
      }));
    }

    getAllUsers(){
      return this._apiService.get(this._config.user_url)
      .pipe(map(users => {
        return users;
      }));
    }

  
}