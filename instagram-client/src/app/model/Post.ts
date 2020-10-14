import { User } from './User';

export class Post {

    id: number;
    description: string;
    photoPath: string;
    dateTime: Date;
    numLikes: number;
    user: User;
    
}