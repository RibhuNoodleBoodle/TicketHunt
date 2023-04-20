import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) { }

  post(event: any, images: File) {
    const data: FormData = new FormData();
    data.append('file', images);
    data.append('event', JSON.stringify(event));
    /* "http://localhost:8088/eventData/common" */
    return this.http.post('https://tickethunt.stackroute.io/eventData/common', data);
    // let params1 = new HttpParams();

    // params1 = params1.append('eventId', eventData.eventId)
    // params1 = params1.append('email', eventData.email)
    // params1 = params1.append('eventName', eventData.eventName)
    // params1 = params1.append('organizerName', eventData.organizerName)
    // params1 = params1.append('date', eventData.date)
    // params1 = params1.append('time', eventData.time)
    // params1 = params1.append('venue', eventData.venue)
    // params1 = params1.append('image', eventData.image)
    // params1 = params1.append('totalSeat', eventData.totalSeat)
    // params1 = params1.append('eventType', eventData.eventType)

  }
}
