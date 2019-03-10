import { TranslationsPageItem } from './translations/translations.component';
import { PageItem } from './../../shared/page-item';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';

  @Component({
    selector: 'app-spoken-languages',
    template: ' <generic-page [item-list]="menuList()"></generic-page>'
  })
  export class SpokenLanguagesComponent implements OnInit {

    
    constructor() { }
  
    ngOnInit() {
    }
  
  menuList(): PageItem[] {
   let selectedPageItem: PageItem = new TranslationsPageItem();
   selectedPageItem.selected = true;
   
   let pageItems: PageItem[] =  [ 
    selectedPageItem,
    ];

    return pageItems;
  }
}
