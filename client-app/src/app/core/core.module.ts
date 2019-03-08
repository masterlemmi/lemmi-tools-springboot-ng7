
import { AppRoutingModule } from './app-routing.module';
import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { BrowserModule } from '@angular/platform-browser';
import { SharedModule } from 'app/shared/shared.module';

import { MenuDirective } from './menu.directive';

@NgModule({
  
  imports: [
    AppRoutingModule,BrowserModule, SharedModule
  ],
  exports: [
    AppRoutingModule, 
    BrowserAnimationsModule,
    CommonModule,
    HeaderComponent, FooterComponent, HomeComponent,

  ],
  declarations: [HeaderComponent, FooterComponent, HomeComponent, MenuDirective],
  providers: []
})
export class CoreModule {
  constructor (@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error(
        'CoreModule is already loaded. Import it in the AppModule only');
    }
  }
 }
