import { Directive, ElementRef, HostListener, Renderer2, HostBinding, Input, ViewChild } from '@angular/core';
import { hostViewClassName } from '@angular/compiler';


//should toggle the bulma class mennu
@Directive({
  selector: 'a.navbar-burger',
 
})
export class BurgerToggleDirective {

  constructor(private el: ElementRef, private renderer : Renderer2) {
   
 }

// @Input('data-target') target: ElementRef;
@Input('data-target')
@ViewChild('data-target') name: ElementRef;

 @HostListener("click") onclick(){
  console.log("focusing")
  this.renderer.addClass(this.el.nativeElement, 'is-active');
  console.log(JSON.stringify(this.name));
  // this.renderer.addClass(this.target.nativeElement, 'is-active');
  return;
 }

 @HostListener('mouseenter') onMouseEnter() {
 //  console.log("ENETERED")
 // this.highlight('black');
}

@HostListener('mouseleave') onMouseLeave() {
 // console.log("ELFT")
  
 // this.highlight(null);
}


private highlight(color: string) {
  this.el.nativeElement.style.backgroundColor = color;
  //this.el.nativeElement.class= "is-active"
}
}
