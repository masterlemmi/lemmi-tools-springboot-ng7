import { Component, OnInit, Input } from '@angular/core';
import { Person } from '../models/person';

@Component({
  selector: 'person-group',
  templateUrl: './person-group.component.html',
  styleUrls: ['./person-group.component.scss']
})
export class PersonGroupComponent implements OnInit {

  @Input("person-list") personList: Person[]
  @Input("group-name") groupName: string
  hideList:boolean = true;

  constructor() { }

  ngOnInit() {
  }

}
