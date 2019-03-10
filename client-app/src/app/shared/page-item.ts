import { Page } from './page';
import { Component } from '@angular/core';
export class PageItem {
    selected: boolean
    name: string

    constructor(public page: Page, public data: any, public submenu ?: PageItem[]){
      this.name = page.name;
    }
   
  
}
