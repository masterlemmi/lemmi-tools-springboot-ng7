import { InterestsModule } from './../interests/interests.module';
import { DevtoolsModule } from './../devtools/devtools.module';
import { HomeComponent } from './home/home.component';
import { FirstPageComponent } from './../first-page/first-page.component';
import { TestComponent } from '../test/test.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PersonLookupModule} from 'app/person-lookup/person-lookup.module';
import { MyNavComponent } from 'app/my-nav/my-nav.component';
import { DemoComponent } from 'app/demo-google/demo.component';
import {  AuthGuard 
} from './auth-guard.service';



const routes: Routes = [
{ path: 'people', loadChildren: () => PersonLookupModule},
{ path: 'devtools', loadChildren: () => DevtoolsModule},
{ path: 'interests', loadChildren: () => InterestsModule},
  { path: 'test', component: DemoComponent },
  { path: 'private', component: FirstPageComponent, canActivate: [AuthGuard] },
  { path: 'home', component: HomeComponent },
  { path: '', pathMatch: 'full', redirectTo: ''},
  { path: "**",redirectTo:"test"}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
