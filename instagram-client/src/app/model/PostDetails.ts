import { LikeUser } from './LikeUser';
import { User } from './User';

export class PostDetails {

    id: number;
    description: string;
    photoPath: string;
    dateTime: Date;
    numLikes: number;
    user: User;
    comments: Comment[];
    likes: LikeUser[];
    liked: boolean;

    constructor(){
        this.user = new User();
    }
    
}