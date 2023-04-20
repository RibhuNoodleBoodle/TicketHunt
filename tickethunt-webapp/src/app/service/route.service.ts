import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouteService {

  constructor(private routeService: Router) { }

  toConfirmation() {
    this.routeService.navigate(['confirmation']);
  }
}