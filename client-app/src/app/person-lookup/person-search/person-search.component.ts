import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import {
  debounceTime, distinctUntilChanged, switchMap, count
} from 'rxjs/operators';
import { Person } from '../models/person';
import { PersonService } from '../services/person.service';


@Component({
  selector: 'person-search',
  templateUrl: './person-search.component.html',
  styleUrls: ['./person-search.component.scss']
})
export class PersonSearchComponent implements OnInit {

  //showResults:boolean = false;
  people$: Observable<Person[]>;
  private searchTerms = new Subject<string>();
  @Output() searchResultClick = new EventEmitter<Person>();

  constructor(private heroService: PersonService) { }

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  //do nothing with the click, just pass the number back to hosting parent to 
  //decide what to do (e.g. go to profile or add to list)
  onDropDownResultClick(person: Person){
    //clear searchterms
    this.search('');
    this.searchResultClick.emit(person);
  }

  ngOnInit(): void {
    this.people$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => {
         return this.heroService.searchPersones(term)
      }),

    );
  }
}