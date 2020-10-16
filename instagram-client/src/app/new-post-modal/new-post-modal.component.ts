import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-new-post-modal',
  templateUrl: './new-post-modal.component.html',
  styleUrls: ['./new-post-modal.component.css']
})
export class NewPostModalComponent implements OnInit {

  constructor(private _activeModal: NgbActiveModal, private _notifier: NotifierService) { }

  selectedFile: File = null;
  description:string = null;

  ngOnInit(): void {
  }

  close(){
    this._activeModal.close(null);
  }

  onFileSelected($event){
    this.selectedFile = $event.target.files[0];
  }

  postPhoto(){
    if(this.selectedFile == null || this.description == null){
      this._notifier.notify("error","Photo and description are required.");
      setTimeout(() => {
        this._notifier.hideAll();
      }, 3000)
      return;
    }
    const fd = new FormData();
    fd.append("file",this.selectedFile);
    fd.append("description", this.description);
    this._activeModal.close(fd);
  }

}
