import { Component, OnInit, Input, ElementRef, HostBinding, Output, EventEmitter } from '@angular/core';
import { Person } from '../../models/person';

@Component({
  selector: 'person-search-modal',
  templateUrl: './person-search-modal.component.html',
  styleUrls: ['./person-search-modal.component.scss']
})
export class PersonSearchModalComponent implements OnInit {

  @Input('title') modalTitle: string;
  @Input('list') personList: Person[];
  modalIsOpen: boolean;
  tempList: Person[] =  [];

  ngOnInit() {
    this.tempList = this.clone(this.personList);
  }

  toggle(){
    this.modalIsOpen = !this.modalIsOpen;
  }

  close(){
    this.modalIsOpen = false;
  }

  //copy temp set to original reference
  save(){
    this.personList.length = 0; //clear array retaning reference
    this.tempList.forEach(x => this.personList.push(x))
    this.close();
  }

  private clone(list: Person[]): any{
    return list.map(x => Object.assign({}, x));
  }

  //when drop down result is clicked
  addToSet(person: Person){
    this.tempList.push(person)
  }
}
