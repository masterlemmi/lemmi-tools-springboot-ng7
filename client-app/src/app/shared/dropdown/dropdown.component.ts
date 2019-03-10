import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Item } from './item';


@Component({
  selector: 'generic-dropdown',
  templateUrl: './dropdown.component.html',
  styleUrls: ['./dropdown.component.scss']
})
export class DropdownComponent implements OnInit {

  @Input('label')
  label: string = "Title"

  @Input('items')
  dropdownitems: string[] 
  toggle: boolean= false;

  @Output('dropdown-selection')
  dropDownClick = new EventEmitter<string>();

  constructor() { }

  ngOnInit() {

  }

  toggleDropDown(){
    this.toggle = !this.toggle;
  }

  selected(item: string){
    this.toggleDropDown()
    this.dropDownClick.emit(item);
  }

}
