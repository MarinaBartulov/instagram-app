import { Router } from '@angular/router';
import { ConfigService } from './config.service';
import { ApiService } from './api.service';
import { Injectable, OnInit } from '@angular/core';
import {HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';



@Injectable()
export class AuthService implements OnInit{


  ngOnInit(): void {
    localStorage.setItem('access_token', null);
    localStorage.setItem('role', null);
  }

    constructor(
        private _apiService: ApiService,
        private _config: ConfigService,
        private _router: Router
      ) {
      }


      login(user) {
        const loginHeaders = new HttpHeaders({
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        });
        const body = {
          'username' : user.username,
          'password' : user.password
        };
        return this._apiService.post(this._config.login_url, JSON.stringify(body), loginHeaders)
          .pipe(
            map(res => {
                console.log('Login success');
                console.log(res)
                localStorage.setItem('access_token', res.accessToken);
                localStorage.setItem('role', res.role);
                console.log("Access token:" +  localStorage.getItem('access_token'));
                console.log("Role: " + localStorage.getItem('role'));
          }));
      }

      logout() {
        localStorage.setItem('access_token', null);
        localStorage.setItem('role', null);
        this._router.navigate(['/login']);
        console.log("Access token:" + localStorage.getItem('access_token'));
        console.log("Role: " + localStorage.getItem('role'));
    }

    signup(user){
      const loginHeaders = new HttpHeaders({
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      });
      return this._apiService.post(this._config.signup_url, JSON.stringify(user), loginHeaders)
          .pipe(
            map(res => {
                console.log(res);
          }));
    }

    

    tokenIsPresent() {
      let token = localStorage.getItem('access_token'); 
      return token != 'null' && token != undefined;
    }
  
    getToken() {
      return localStorage.getItem('access_token');
    }

    }
