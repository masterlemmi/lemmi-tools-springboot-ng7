import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  user = {}
  constructor(public authService: AuthService, public router: Router) { }

  ngOnInit() {
    this.authService.login().subscribe(() => {
      console.log("oninit login");
      if (this.authService.isLoggedIn) {
        console.log("received resonse")
        this.user = this.authService.user;
      }
    });
  }

  logout() {
    this.authService.logout();

  }


  toggleIt() {
    this.toggled = !this.toggled;
  }

  toggled: boolean = false;

}
