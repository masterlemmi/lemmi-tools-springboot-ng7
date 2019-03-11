import { PageItem } from './../../../shared/page-item';
import { Page } from 'app/shared/page';
import { PAGE_DATA } from './../../../shared/tokens';
import { PageData } from './../../../shared/page-data';
import { Component, OnInit, Input, Inject } from '@angular/core';
import { PluginService } from './plugin.service';
import {Plugin} from './plugin'

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
//This class is a subcomponent for hte parent class idescomponent.ts
export class PluginsComponent implements OnInit{

  @Input('ide')
  chosenIde: string = "All";

  plugins: Plugin[] = [];

  static pageInfo(n?:string): Page {
    let name = n || "plugins";
    return new Page(name, PluginsComponent)
  }

  //get the injected data from generic page ts sent from Ides COmponent
  constructor(@Inject(PAGE_DATA) private inputData: any,
        private pluginService: PluginService) { }
 
  ngOnInit() {
    this.chosenIde = this.inputData || this.chosenIde;
    this.pluginService.getAll().subscribe(res=>{
      console.log("plguin response", res);
      this.plugins =res;
      this.plugins.forEach(plugin=>{
        plugin.image = 'https://bulma.io/images/placeholders/128x128.png'
        plugin.description = "Lorem ipsum, dolor sit amet consectetur adipisicing elit. Officiis explicabo in soluta laudantium. Aperiam sunt sint facilis neque, adipisci nemo, labore aspernatur iure dolorem earum, perspiciatis nihil dolorum. Ex, ea!"
      });
    })
  }


  name:string = "plugins";
  
}
