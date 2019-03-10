import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'my-nav',
  templateUrl: './my-nav.component.html',
  styleUrls: ['./my-nav.component.css']
})
export class MyNavComponent {
  constructor() {}
  getAnimationData(outlet: RouterOutlet) {  //retrieves the animation data passed in the router 
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation'];
  }
}
