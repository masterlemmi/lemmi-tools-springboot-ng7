import { Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { PersonService } from '../services/person.service';
import { switchMap } from 'rxjs/operators';
import { Person } from '../models/person';
import { Observable } from 'rxjs';
import { ImageModalComponent } from 'app/shared/image-modal/image-modal.component';

@Component({
  selector: 'person-detail',
  templateUrl: './person-detail.component.html',
  styleUrls: ['./person-detail.component.scss']
})
export class PersonDetailComponent implements OnInit {

  isEditing = true;

  @ViewChild(ImageModalComponent) imageModal: ImageModalComponent;
  
  person$: Observable<Person>
  objectKeys = Object.keys;
  name:string

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: PersonService
  ) {}

  ngOnInit() {
    this.person$ = this.route.paramMap.pipe(
      switchMap((params: ParamMap) => this.service.getPerson(+params.get('personId'))
      ));
  }

  searchFB(person: Person){
    return `https://www.facebook.com/search/top/?q=${person.name}&epa=SEARCH_BOX`;
  }

  investigatePic(){
    this.imageModal.toggle();
  }

}
