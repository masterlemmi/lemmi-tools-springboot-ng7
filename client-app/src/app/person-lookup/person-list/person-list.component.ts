import { Component, OnInit } from '@angular/core';
import { PersonService } from '../services/person.service';
import { Person } from '../models/person';

@Component({
  selector: 'person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.scss']
})
export class PersonListComponent implements OnInit {

  people: Array<Person>

  constructor(private personService: PersonService) { }

  ngOnInit() {
    this.personService.getAll().subscribe( res => {
        this.people = res;
    })
  }

}
