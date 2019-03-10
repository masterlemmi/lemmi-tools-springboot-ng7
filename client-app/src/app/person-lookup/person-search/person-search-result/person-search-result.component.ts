import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Person } from 'app/person-lookup/models/person';

@Component({
  selector: 'person-search-result',
  templateUrl: './person-search-result.component.html',
  styleUrls: ['./person-search-result.component.scss']
})
export class PersonResultComponent {

  @Input() person: Person;
  @Output() dropDownClick = new EventEmitter<Person>(); 

  personChosen(person: Person){
   this.dropDownClick.emit(person);
  }
}
