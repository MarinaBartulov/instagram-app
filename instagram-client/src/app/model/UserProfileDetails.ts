import { Post } from './Post';
import { PostSimple } from './PostSimple';
import { UserFollow } from './UserFollow';

export class UserProfileDetails {

    user: UserFollow;
    posts: PostSimple[];

    constructor(){
        this.user = new UserFollow();
    }
   
}