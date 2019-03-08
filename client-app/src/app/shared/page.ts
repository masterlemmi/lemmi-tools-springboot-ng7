import { PageData } from './page-data';
import { Component } from '@angular/core';
export class Page {

    active: boolean = false;
    data: PageData; //data to be injected in the dynamically inserted component

    constructor(public name: string, public component: any){
       
    }
   
  
}
