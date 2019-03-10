import { InsideTestComponent } from './inside-test/inside-test.component';

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PersonListComponent } from './person-list/person-list.component';
import { PersonDetailComponent } from './person-detail/person-detail.component';
import { PersonEditComponent } from './person-edit/person-edit.component';
import { PersonSearchComponent } from './person-search/person-search.component';
import { LookupContainerComponent } from './lookup-container/lookup-container.component';

const routes: Routes = [
  { path: '', component: LookupContainerComponent, data: { animation: 'people' } }, // default route of the module
  { path: 'recent', component: PersonListComponent, data: { animation: 'people' } }, 
  { path: 'id/:personId', component: PersonDetailComponent, data: { animation: 'person' } },
  { path: 'edit/:personId', component: PersonEditComponent, data: { animation: 'person' } },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PersonLookupRoutingModule { }
