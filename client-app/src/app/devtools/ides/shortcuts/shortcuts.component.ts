import { PageItem } from './../../../shared/page-item';

import { Component, OnInit } from '@angular/core';
import { Page } from 'app/shared/page';
import { ShortcutService } from './shortcut.service';

export class ShortcutsPageItem extends PageItem{
  
  constructor(name? : string){
    let defaultName = name || "shortcuts"
    let page = new Page(defaultName, ShortcutsComponent);

    super(page, "");
  }
}

@Component({
  selector: 'ide-shortcuts',
  templateUrl: './shortcuts.component.html',
  styleUrls: ['./shortcuts.component.scss']
})
//This class is a subcomponent for hte parent class idescomponent.ts
export class ShortcutsComponent implements OnInit{

  headers = []

  data: any[] = []

  constructor(private shortcutService: ShortcutService) { }

  ngOnInit() {
    this.shortcutService.getAll().subscribe(res=>{
      this.data = res;
      this.headers = this.getHeaders(res);
    })
  }

  getKeys(obj: any): any[] {
    let allKeys: any[] = Object.keys(obj);
    allKeys.splice(0, 1);
    return allKeys;
  }

  getHeaders(res : any): any[]{
    if (res){
      return this.getKeys(res[0]);
    }

    return [];
  }



}