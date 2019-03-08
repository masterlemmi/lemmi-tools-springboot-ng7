import { Component, AfterViewInit, ElementRef } from '@angular/core';
declare const gapi: any;

@Component({
  selector: 'google-signin',
  template: '<button id="googleBtn" (click)="grantOfflineAccess()"   >Google Sign-In</button>'
})
export class DemoComponent implements AfterViewInit {

  private clientId:string = '729817514724-7jp1jis81fiuk4pvkiqc5nk2kspqev22.apps.googleusercontent.com';

  private scope = [
    'profile',
    'email',
    'https://www.googleapis.com/auth/plus.me',
    'https://www.googleapis.com/auth/contacts',
    'https://www.googleapis.com/auth/contacts.readonly',
    'https://www.googleapis.com/auth/admin.directory.user.readonly',
    'https://www.googleapis.com/auth/drive.appfolder'
  ].join(' ');

  public auth2: any;

  ngAfterViewInit() {
    this.googleInit();
  }

  public googleInit() {        
    gapi.load('auth2', () => {
      this.auth2 = gapi.auth2.init({
        client_id: this.clientId,
        cookiepolicy: 'single_host_origin',
        scope: this.scope
      });
      this.attachSignin(this.element.nativeElement.firstChild);
    });
  }

  grantOfflineAccess(){
    this.auth2.grantOfflineAccess().then(this.signInCallback);
  }

  signInCallback(authResult) {
    if (authResult['code']) {
        console.log("received", authResult['code'])
      // Hide the sign-in button now that the user is authorized, for example:
     // $('#signinButton').attr('style', 'display: none');
  
      // // Send the code to the server
      // $.ajax({
      //   type: 'POST',
      //   url: 'http://example.com/storeauthcode',
      //   // Always include an `X-Requested-With` header in every AJAX request,
      //   // to protect against CSRF attacks.
      //   headers: {
      //     'X-Requested-With': 'XMLHttpRequest'
      //   },
      //   contentType: 'application/octet-stream; charset=utf-8',
      //   success: function(result) {
      //     // Handle or verify the server response.
      //   },
      //   processData: false,
      //   data: authResult['code']
      // });
    } else {
      // There was an error.
      console.log("THERE WAS AN ERROR VALIDATING SIGN IN");
    }
  
  }


  public attachSignin(element) {
    this.auth2.attachClickHandler(element, {},
      (googleUser) => {
        JSON.stringify(googleUser);
        let profile = googleUser.getBasicProfile();
        console.log('Token || ' + googleUser.getAuthResponse().id_token);
        console.log('ID: ' + profile.getId());
        // ...
      }, function (error) {
        console.log("ERROR", JSON.stringify(error, undefined, 2));
      });
  }

  constructor(private element: ElementRef) {
    console.log('ElementRef: ', this.element);
  }


}