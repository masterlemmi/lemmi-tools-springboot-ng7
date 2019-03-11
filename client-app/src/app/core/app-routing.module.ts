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
import { NotfoundComponent } from 'app/shared/notfound/notfound.component';



const routes: Routes = [
//feature is pending
// { path: 'people', loadChildren: "app/person-lookup/person-lookup.module#PersonLookupModule"},
{ path: 'people', component: NotfoundComponent },
{ path: 'devtools', loadChildren: "../devtools/devtools.module#DevtoolsModule"},
{ path: 'interests', loadChildren: "../interests/interests.module#InterestsModule"},
  { path: 'demo', component: DemoComponent },
  { path: 'notfound', component: NotfoundComponent },
  //{ path: 'private', component: FirstPageComponent, canActivate: [AuthGuard] },
  { path: 'private', component: NotfoundComponent },
  { path: 'home', component: HomeComponent },
  { path: '', pathMatch: 'full', redirectTo: 'home'},
  { path: "**",redirectTo:"error"}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
