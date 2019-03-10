import { Directive, HostListener } from '@angular/core';

@Directive({
  selector: '[noPropagationClick]'
})
export class NoPropagationClickDirective {
  @HostListener("click", ["$event"])
  public onClick(event: any): void {
    event.stopPropagation();
  }
}