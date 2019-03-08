import { Component, OnInit } from '@angular/core';
import { Person } from '../models/person';
import { Router } from '@angular/router';

@Component({
  selector: 'person-lookup',
  templateUrl: './lookup-container.component.html',
  styleUrls: ['./lookup-container.component.scss']
})
export class LookupContainerComponent  {

  constructor(private router: Router) { }

  getProfile(person:Person){
    const id = person.id;
    this.router.navigate(['/people/id', id])
  }
}
