import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Person } from 'app/person-lookup/models/person';
import { PersonSearchModalService } from 'app/person-lookup/person-search/person-search-modal.service';
import { PersonSearchModalComponent } from 'app/person-lookup/person-search/person-search-modal/person-search-modal.component';
import { TitleCasePipe } from '@angular/common';

@Component({
  selector: 'person-edit-inline-list',
  templateUrl: './inline-list.component.html',
  styleUrls: ['./inline-list.component.scss']
})
export class InlineEditListComponent implements OnInit {

  @Input('list') peopleList: Person[];
  private _modalTitle: string;

  constructor(private modalService: PersonSearchModalService) { }

  ngOnInit() {
  }

  @Input("person-type") 
  set modalTitle(personType:  string){
    this._modalTitle = "Add " + new TitleCasePipe().transform(personType);
  }

  get modalTitle() {return this._modalTitle}

  openModal(modal: PersonSearchModalComponent){
    modal.toggle();
  }

  onSaveClicked(){
    console.log("listened to save click")
  }

}
