import { Directive, ElementRef, HostListener, Renderer2, HostBinding } from '@angular/core';
import { hostViewClassName } from '@angular/compiler';


//should toggle the bulma class mennu
@Directive({
  selector: 'input.input'
})
export class InputFocusDirective {
  private el: ElementRef;
  constructor(private _el: ElementRef, public renderer : Renderer2) {
   this.el =_el;
 }


  @HostListener('focus', ['$event']) onFocus(e) {
    console.log("focusing")
    this.renderer.addClass(this._el.nativeElement, 'is-focused');
    return;
}
}
