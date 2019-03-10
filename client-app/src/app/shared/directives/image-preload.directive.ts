

import {Directive, Input, HostBinding} from '@angular/core'

@Directive({
    selector: 'img[default]',
    host: {
      '(error)':'updateUrl()',
      '(load)': 'load()',
      '[src]':'src'
     }
  })
  
 export class ImagePreloadDirective {
    @Input() src:string;
    @Input() default:string;
   // @HostBinding('class') className
  
    updateUrl() {
      this.src = this.default ? this.default: "assets/no-photo.png";
    }
    load(){
     // this.className = 'image-loaded';
    }
  }