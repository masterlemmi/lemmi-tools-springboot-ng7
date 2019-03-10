import { Item } from './../../../shared/dropdown/item';
import { PageItem } from './../../../shared/page-item';

import { Component, OnInit } from '@angular/core';
import { Page } from 'app/shared/page';

export class TranslationsPageItem extends PageItem{
  
  constructor(name? : string){
    let defaultName = name || "translations"
    let page = new Page(defaultName, TranslationsComponent);

    super(page, "");
  }
}

@Component({
  selector: 'word-translations',
  templateUrl: './translations.component.html',
  styleUrls: ['./translations.component.scss']
})
export class TranslationsComponent implements OnInit{

  toggle: boolean 

  languages: any = ["Cebuano", "French", "Japanese", "Ilocano"]
  selectedLanguage: string = "Cebuano";
  keys: string[] = ["cebuano", "english"]

  onDropDownSelection(item:string){
    this.keys[0] = item.toLowerCase();
    this.selectedLanguage = item
  }


  data: any[] = [
    {cebuano: "iring iring iring iring iring irng lala", english: "cat", french: "cateoux", japanese: "catumasu", ilocano: "pusha", tags:["color", "expression", "animals"]},
    {cebuano: "iring iring iring iring iring irng lala", english: "cat", french: "cateoux", japanese: "catumasu", ilocano: "pusha", tags:["people", "pronoun", "animals"]},
    {cebuano: "iring iring iring iring iring irng lala", english: "cat", french: "cateoux", japanese: "catumasu", ilocano: "pusha", tags:["things", "expression"]},
    {cebuano: "iring iring iring iring iring irng lala", english: "cat", french: "cateoux", japanese: "catumasu", ilocano: "pusha", tags:["day"]}                                                                                               
]

  constructor() { }

  ngOnInit() {
  }

  getKeys(): any[] {
    return [];
  }

  

}