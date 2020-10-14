import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';

@Injectable()
export class PostService{

    constructor(private _apiService:ApiService, private _config: ConfigService){

    }

    getPosts(id:number) {
        return this._apiService.get(this._config.post_url + "/user/" + id)
          .pipe(map(posts => {
            return posts;
          }));
      }

      getPostDetails(id:number) {
        return this._apiService.get(this._config.post_url + "/" + id + "/details")
          .pipe(map(post => {
            return post;
          }));
      }  

      deletePost(id:number){
        return this._apiService.delete(this._config.post_url + "/" + id)
          .pipe(map(post => {
            return post;
          }));
      }

      getPostsFeed(){
        return this._apiService.get(this._config.post_url + "/feed")
          .pipe(map(posts => {
            return posts;
          }));
      }

  
}