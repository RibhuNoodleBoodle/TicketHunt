import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LocationService {
  constructor(private http: HttpClient) { }

  getLocation(position: GeolocationPosition) {
    let latitude: number = position.coords.latitude;
    let longitude: number = position.coords.longitude;
    return this.http.get(
      'https://api.opencagedata.com/geocode/v1/json?q=' +
      latitude +
      '%2C+' +
      longitude +
      '&key=d9acb9db4f6d43a199639772e1a9ddd1&pretty=1'
    );
  }
}
