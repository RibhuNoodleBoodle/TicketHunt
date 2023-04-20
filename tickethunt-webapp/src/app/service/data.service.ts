import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Movie } from '../model/movie.model';
import { Observable } from 'rxjs';
import { EventData } from '../model/event';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  constructor(private http: HttpClient) { }

  getAllMovies() {
    return this.http.get<Array<Movie>>('https://tickethunt.stackroute.io/movies');
  }

  getMovieById(id: number) {
    return this.http.get<Array<Movie>>(
      'https://tickethunt.stackroute.io/movies/?id=' + id
    );
  }

  getAllEvents() {
    return this.http.get<Array<Event>>('https://tickethunt.stackroute.io/eventData/event');
  }
  getAllEvent() {
    return this.http.get<Array<EventData>>(
      'https://tickethunt.stackroute.io/eventData/event'
    );
  }

  editPost(id?: string, event?: any) {
    return this.http.put<Event>(
      'https://tickethunt.stackroute.io/eventData/updateEvent/' + id,
      event
    );
  }

  getEvent1(id?: any): Observable<Event> {
    return this.http.get<Event>(
      `${'https://tickethunt.stackroute.io/eventData/getEvent1'}/${id}`
    );
  }

  deletePost(id?: string) {
    console.log('test deletepost');
    return this.http.delete('https://tickethunt.stackroute.io/eventData/delete/' + id);
  }

  getEventByEmail(emailId: string) {
    return this.http.get('https://tickethunt.stackroute.io/eventData/event/' + emailId);
  }

  getAllEvents3() {
    return this.http.get<Array<Event>>(
      'https://tickethunt.stackroute.io/eventData/getEvent/movie'
    );
  }
  getAllEvents2() {
    return this.http.get<Array<Event>>(
      'https://tickethunt.stackroute.io/eventData/getEvent/event'
    );
  }
}
