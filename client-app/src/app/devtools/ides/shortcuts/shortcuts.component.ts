import { PageItem } from './../../../shared/page-item';

import { Component, OnInit } from '@angular/core';
import { Page } from 'app/shared/page';

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
export class ShortcutsComponent implements OnInit{

  headers = ["Eclipse", "Intelliyyyy", "VsCode", "Description"]



  data: any[] = [
    {
      name: "Lem",
      desc: "in Excelsis deo balblabla",
      desc2: "in Excelsis deo balblabla",
      id: "tatae tal al al a"
    },{
      name: "Lem",
      desc: "in Excelsis deo balblabla",
      desc2: "in Excelsis deo balblabla",
      id: "tatae tal al al a"
    },{
      name: "Lem",
      desc: "in Excelsis deo balblabla",
      desc2: "in Excelsis deo balblabla",
      id: "tatae tal al al a"
    },{
      name: "Lem",
      desc: "in Excelsis deo balblabla",
      desc2: "in Excelsis deo balblabla",
      id: "tatae tal al al a"
    },
]

  constructor() { }

  ngOnInit() {
  }

  getKeys(obj: any): any[] {
    return Object.keys(obj);
  }



}