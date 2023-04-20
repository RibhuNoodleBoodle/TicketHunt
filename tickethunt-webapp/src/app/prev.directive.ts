import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appPrev]'
})
export class PrevDirective {

  constructor(private el: ElementRef) { }

  @HostListener('click')
  prevFunction() {
    var elm = this.el.nativeElement.parentElement.parentElement.children[0];
    var event = elm.getElementsByClassName("event")
    //console.log(movie)
    elm.prepend(event[event.length - 1])
  }


}
