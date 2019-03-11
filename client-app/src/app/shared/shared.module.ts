import { Tokens } from './tokens';
/* 3rd party libraries */
import { NgModule, InjectionToken } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ImagePreloadDirective } from './directives/image-preload.directive';
import { FormsModule } from '@angular/forms';
import { BurgerToggleDirective } from './directives/burger-toggle.directive';
import { InputFocusDirective } from './directives/input-focus.directive';
import { ImageModalComponent } from './image-modal/image-modal.component';
import { TableComponent } from './table/table.component';
import { NoPropagationClickDirective } from './directives/no-propagation-click.directive';
import { GenericPageComponent } from './generic-page/generic-page.component';

import { DropdownComponent } from './dropdown/dropdown.component';

import { NotfoundComponent } from './notfound/notfound.component';

/* our own custom components */
//import { SomeCustomComponent } from './some-custom/some-custom.component';



@NgModule({
  imports: [
    /* angular stuff */
    HttpClientModule, CommonModule,
    
  ],
  declarations: [
    //SomeCustomComponent
    ImagePreloadDirective,
    BurgerToggleDirective, InputFocusDirective, ImageModalComponent, TableComponent, 
    NoPropagationClickDirective, GenericPageComponent,  DropdownComponent, NotfoundComponent,       
  ],
  exports: [
    /* angular stuff */
    HttpClientModule,
    FormsModule,
    ImagePreloadDirective, BurgerToggleDirective, InputFocusDirective,
    ImageModalComponent, TableComponent, CommonModule, NoPropagationClickDirective, GenericPageComponent, 
    DropdownComponent,
  ]
})
export class SharedModule { }