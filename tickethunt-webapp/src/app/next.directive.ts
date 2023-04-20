import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appNext]'
})
export class NextDirective {


  constructor(private el: ElementRef) {

  }

  @HostListener('click')
  nextFunction() {
    var elm = this.el.nativeElement.parentElement.parentElement.children[0];
    var event = elm.getElementsByClassName("event")
    console.log(event)
    elm.append(event[0])
  }

}
