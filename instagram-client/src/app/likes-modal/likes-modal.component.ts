import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { LikeUser } from '../model/LikeUser';

@Component({
  selector: 'app-likes-modal',
  templateUrl: './likes-modal.component.html',
  styleUrls: ['./likes-modal.component.css']
})
export class LikesModalComponent implements OnInit {


  constructor(private _activeModal: NgbActiveModal) { }

  @Input() likes: LikeUser;

  ngOnInit(): void {
  }

  close(){
    this._activeModal.close(null);
 }


}
