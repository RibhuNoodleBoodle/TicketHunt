import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, retry } from 'rxjs';
import { RecEvent } from '../model/recEvent.model';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root',
})
export class RecommendationService {
  constructor(private http: HttpClient) { }

  getAllEvents(): Observable<Array<RecEvent>> {
    return this.http.get<Array<RecEvent>>(
      'https://tickethunt.stackroute.io/api/v4/events'
    );
  }

  getAllUsers(): Observable<Array<User>> {
    return this.http.get<Array<User>>('https://tickethunt.stackroute.io/api/v4/users');
  }

  addBooking(user: string) {
    return this.http.get('https://tickethunt.stackroute.io/api/v4/user/booked/' + user);
  }
}
