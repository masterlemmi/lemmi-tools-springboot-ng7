import { PageItem } from './../../../shared/page-item';
import { Page } from 'app/shared/page';
import { PAGE_DATA } from './../../../shared/tokens';
import { PageData } from './../../../shared/page-data';
import { Component, OnInit, Input, Inject } from '@angular/core';

export class PluginsPageItem extends PageItem{

  constructor(name? : string, submenu?:PageItem[]){
    let defaultName = name || "plugins"
    let page = new Page(defaultName, PluginsComponent);

    let data = name == "plugins" ? "All" : name // used as chosenIde

    super(page, data, submenu);
  }
}
 
@Component({
  selector: 'ide-plugins',
  templateUrl: './plugins.component.html',
  styleUrls: ['./plugins.component.scss']
})
export class PluginsComponent implements OnInit{

  @Input('ide')
  chosenIde: string = "All";

  static pageInfo(n?:string): Page {
    let name = n || "plugins";
    return new Page(name, PluginsComponent)
  }

  //get the injected data from generic page ts sent from IDES TS
  constructor(@Inject(PAGE_DATA) private inputData: any) { }
 
  ngOnInit() {
    this.chosenIde = this.inputData || this.chosenIde;
  }


  name:string = "plugins";
  
}
