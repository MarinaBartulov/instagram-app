import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; 

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AuthService } from './service/auth.service';
import { ConfigService } from './service/config.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SignupComponent } from './signup/signup.component';
import { RegConfirmationComponent } from './signup/reg-confirmation/reg-confirmation.component';
import { UserService } from './service/user.service';
import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { NotifierModule, NotifierOptions } from 'angular-notifier';
import { ProfileComponent } from './profile/profile.component';
import { PostService } from './service/post.service';
import { FollowService } from './service/follow.service';
import { PostDetailsComponent } from './post-details/post-details.component';
import { ProfilePostsComponent } from './profile-posts/profile-posts.component';
import { CommentService } from './service/comment.service';
import { LikeService } from './service/like.service';
import { PostFeedComponent } from './post-feed/post-feed.component';
import { SearchUsersComponent } from './search-users/search-users.component';


const customNotifierOptions: NotifierOptions = {
  position: {
		horizontal: {
			position: 'right',
			distance: 12
		},
		vertical: {
			position: 'top',
			distance: 100,
			gap: 10
		}
	},
  theme: 'material',
  behaviour: {
    autoHide: 5000,
    onClick: 'hide',
    onMouseover: 'pauseAutoHide',
    showDismissButton: true,
    stacking: 4
  },
  animations: {
    enabled: true,
    show: {
      preset: 'slide',
      speed: 300,
      easing: 'ease'
    },
    hide: {
      preset: 'fade',
      speed: 300,
      easing: 'ease',
      offset: 50
    },
    shift: {
      speed: 300,
      easing: 'ease'
    },
    overlap: 150
  }
};


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    SignupComponent,
    RegConfirmationComponent,
    ProfileComponent,
    PostDetailsComponent,
    ProfilePostsComponent,
    PostFeedComponent,
    SearchUsersComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NotifierModule.withConfig(customNotifierOptions),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    ConfigService,
    AuthService,
    UserService,
    PostService,
    FollowService,
    CommentService,
    LikeService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
