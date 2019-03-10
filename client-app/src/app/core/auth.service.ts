import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { delay, tap , catchError, map} from 'rxjs/operators';
//import { JwtHelperService } from '@auth0/angular-jwt';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageService } from './message.service';
import { User } from './user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
    providedIn: 'root',
  })

  export class AuthService {

  private userLink = 'api/lemmitools/user';  // URL to web api
  isLoggedIn = false;
  user: User;

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    // Check whether the token is expired and return
    // true or false
   // return !this.jwtHelper.isTokenExpired(token);
   return false;
  }


  // store the URL so we can redirect after logging in
  redirectUrl: string;

  login(): Observable<any> {
    console.log("login from auhtservice")
    return this.http.get<any>(this. userLink).pipe(
      delay(1000),
      tap(data => {
        console.log(data)
        this.isLoggedIn = true;
        this.user = data;
      }), catchError((er, ca) => {
        this.isLoggedIn = false;
        return of("lemobservablestring");
      })
    )
  }

  private handleFailedLogin(){
    
    console.log("failed login");
    return of(null);
  }
  

  logout(): void {
    this.isLoggedIn = false;
  }


  

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

 

}