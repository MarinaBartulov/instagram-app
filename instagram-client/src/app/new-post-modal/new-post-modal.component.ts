import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-new-post-modal',
  templateUrl: './new-post-modal.component.html',
  styleUrls: ['./new-post-modal.component.css']
})
export class NewPostModalComponent implements OnInit {

  constructor(private _activeModal: NgbActiveModal) { }

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
    const fd = new FormData();
    fd.append("file",this.selectedFile);
    fd.append("description", this.description);
    this._activeModal.close(fd);
  }

}
