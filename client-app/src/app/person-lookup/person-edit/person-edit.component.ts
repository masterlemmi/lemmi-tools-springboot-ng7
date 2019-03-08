import { Component, OnInit, AfterViewInit, Input } from '@angular/core';
import { switchMap } from 'rxjs/operators';
import { ParamMap, ActivatedRoute, Router } from '@angular/router';
import { PersonService } from '../services/person.service';
import { Person } from '../models/person';
import { Observable } from 'rxjs';
declare const bulmaCalendar: any;

@Component({
  selector: 'person-edit',
  templateUrl: './person-edit.component.html',
  styleUrls: ['./person-edit.component.scss']
})
export class PersonEditComponent implements OnInit {

  @Input()
  person: Person;
  objectKeys = Object.keys;
  name: string
  chosenD

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: PersonService
  ) { }

  ngOnInit() {
   

    // // Initialize all input of date type.
    // const calendars = bulmaCalendar.attach('[type="date"]', {});
    // calendars.format = "DD/MM/YY"

    // // Loop on each calendar initialized
    // // // Add listener to date:selected event
    // // calendars.on('date:selected', date => {
    // //   console.log(date);
    // // });

    // for (var i = 0; i < calendars.length; i++) { // Add listener to date:selected event calendars[i].on('date:selected',
    //   date => {
    //     console.log(date);
    //   }
    // }
    // }

    }
  }