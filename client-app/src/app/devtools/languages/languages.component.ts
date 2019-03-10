import { PageItem } from './../../shared/page-item';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';

  @Component({
    selector: 'app-languages',
    template: ' <generic-page [item-list]="menuList()"></generic-page>'
  })
  export class LanguagesComponent implements OnInit {
  
    constructor() { }
  
    ngOnInit() {
    }
  
  menuList(): PageItem[] {
  
  return [];
  }
}
