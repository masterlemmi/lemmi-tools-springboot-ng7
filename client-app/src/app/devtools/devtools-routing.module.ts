import { LanguagesComponent } from './languages/languages.component';

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DevToolsHomeComponent } from './dev-tools-home/dev-tools-home.component';
import { IdesComponent } from './ides/ides.component';


const routes: Routes = [
  { path: '', component: DevToolsHomeComponent, children: [ 
  { path: 'languages', component: LanguagesComponent}, 
  { path: 'ides', component: IdesComponent }
]},
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DevToolsRoutingModule { }
