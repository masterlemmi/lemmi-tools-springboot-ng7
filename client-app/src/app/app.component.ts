import { Component } from '@angular/core';
import { slideInAnimation } from './person-lookup/animations';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  animations: [ slideInAnimation ]
})
export class AppComponent {
  getAnimationData(outlet: RouterOutlet) {  //retrieves the animation data passed in the router 
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation'];
}

}
