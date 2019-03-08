import { ShortcutsComponent, ShortcutsPageItem } from './shortcuts/shortcuts.component';
import { PageItem } from './../../shared/page-item';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { PluginsComponent, PluginsPageItem } from './plugins/plugins.component';

@Component({
  selector: 'app-ides',
  template: ' <generic-page [item-list]="menuList()"></generic-page>'
})
export class IdesComponent{

  menuList(): PageItem[] {
   let selectedPageItem: PageItem = new ShortcutsPageItem();
   selectedPageItem.selected = true;
   
   let pageItems: PageItem[] =  [ 
    selectedPageItem,
    new PluginsPageItem("plugins", [
      new PluginsPageItem("eclipse"),  
      new PluginsPageItem("intellij"),  
      new PluginsPageItem("vscode")
      ])
    ];

    return pageItems;
  }
}
