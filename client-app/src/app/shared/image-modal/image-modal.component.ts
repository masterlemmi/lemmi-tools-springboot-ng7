import { Component, OnInit, Input, Output, EventEmitter, HostListener, HostBinding } from '@angular/core';

@Component({
  selector: 'image-modal',
  templateUrl: './image-modal.component.html',
  styleUrls: ['./image-modal.component.scss']
})
export class ImageModalComponent {

  @Input('src') imageSrc: string;
  @Input("is-active") isActive: boolean = false;
  
  close(){
    this.isActive = false;
  }

  toggle(){
    this.isActive = !this.isActive;
  }

} 
