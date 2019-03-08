import { PAGE_DATA } from './../tokens';
import { PageData } from './../page-data';
import { Page } from 'app/shared/page';

import { PageItem } from './../page-item';
import { Component, OnInit, Input, Injector, ReflectiveInjector, InjectionToken } from '@angular/core';



@Component({
  selector: 'generic-page',
  templateUrl: './generic-page.component.html',
  styleUrls: ['./generic-page.component.scss']
})
export class GenericPageComponent implements OnInit {

  @Input('item-list')
  menuList: PageItem[] = []

  activePage: Page //the component to display
  pageInjector: Injector //used to inject data to active page

  constructor(private injector: Injector) {}

  ngOnInit() {
    //show default selected menu
    let selectedMenu: PageItem = this.menuList.filter(menu=>{
      return menu.selected
    })[0];
    this.show(selectedMenu);
  }
  

  show(menu: PageItem) {
   this.activePage =  menu.page;
   this.pageInjector = ReflectiveInjector.resolveAndCreate([{ provide: PAGE_DATA, useValue: menu.data }], this.injector);
  }

}
