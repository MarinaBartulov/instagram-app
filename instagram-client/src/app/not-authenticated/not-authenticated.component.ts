import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-not-authenticated',
  templateUrl: './not-authenticated.component.html',
  styleUrls: ['./not-authenticated.component.css']
})
export class NotAuthenticatedComponent implements OnInit {

  constructor(private _router: Router) { }

  ngOnInit() {
  }

  goToLogin(){
    this._router.navigate(['/login']);
  }

}
