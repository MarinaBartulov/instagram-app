import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
  })
export class ConfigService {

    private _server_url = 'http://localhost:8080/api';
    private _auth_url = this.server_url + "/auth";
    private _login_url = this._auth_url + '/login';
    private _signup_url = this._auth_url + '/signup';
    private _currentUser_url = this._server_url + '/user/currentUser';
    private _confirmationReg_url = this._auth_url + '/confirmRegistration';
    private _post_url = this.server_url + "/post";
    private _user_url = this.server_url + "/user";
    private _follow_url = this.server_url + "/follow";
    private _comment_url = this.server_url + "/comment";
    private _like_url = this.server_url + "/like";



    get server_url():string{
        return this._server_url;
      }

    get login_url(): string {
        return this._login_url;
    }

    get signup_url(): string {
        return this._signup_url;
    }

    get currentUser_url(): string {
      return this._currentUser_url;
    }

    get confirmationReg_url(): string {
      return this._confirmationReg_url;
    }

    get post_url(): string {
      return this._post_url;
    }

    get user_url(): string {
      return this._user_url;
    }

    get follow_url(): string {
      return this._follow_url;
    }

    get comment_url(): string {
      return this._comment_url;
    }

    get like_url(): string {
      return this._like_url;
    }

   

}