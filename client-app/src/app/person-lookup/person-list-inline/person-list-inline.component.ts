import { Component, OnInit, Input } from '@angular/core';
import { Person } from '../models/person';

@Component({
  selector: 'person-list-inline',
  templateUrl: './person-list-inline.component.html',
  styleUrls: ['./person-list-inline.component.scss']
})
export class PersonListInlineComponent implements OnInit {

  @Input("list") personList: Person[]

  constructor() { }

  ngOnInit() {
  }

}
