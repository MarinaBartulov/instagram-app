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

   

}