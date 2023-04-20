import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Bookings, Seats } from '../model/bookings';


@Injectable({
  providedIn: 'root'
})
export class BookingServiceService {

  constructor(private http: HttpClient) { }

  url = "https://tickethunt.stackroute.io/bookings/view/";

  id = localStorage.getItem('id');

  email = sessionStorage.getItem('emailId')
  /*  bookSeat = `http://localhost:8081/bookings/book/1/rohitsnarnaware7@gmail.com`;
 
   bookingHistory = "http://localhost:8081/bookings/rohitsnarnaware7@gmail.com"
 
   cancel = "http://localhost:8081/bookings/cancel/1/qqq/" */

  sendEmail(email: any, message: any) {
    return this.http.post<any>(`https://tickethunt.stackroute.io/bookings/${email}/${message}`, "")
  }

  addBookingForNewEmail(booking: any): Observable<any> {
    return this.http.post<any>('https://tickethunt.stackroute.io/bookings/addBooking', booking);
  }

  getParticularBookings(eventId: any): Observable<any> {
    return this.http.get<Bookings>(this.url + eventId);
  }

  getAllBookings(): Observable<any> {

    return this.http.get<Bookings>(this.url);
  }

  bookSeats(seat: Seats, id: any, email: any): Observable<any> {
    return this.http.post<Seats>(`https://tickethunt.stackroute.io/bookings/book/${id}/${email}`, seat);


  }


  bookingHistoryOfUser(): Observable<any> {

    return this.http.get<Bookings>(`https://tickethunt.stackroute.io/bookings/${this.email}`)
  }

  cancelSeats(seat: any, eventId: any): Observable<any> {

    return this.http.delete<any>(`https://tickethunt.stackroute.io/bookings/cancel/${eventId}/${this.email}/${seat}`)

  }

  findByEmail(email: any): Observable<any> {

    return this.http.get<any>(`https://tickethunt.stackroute.io/bookings/${email}`)
  }

  findByEventIdAndEmail(eventId: string, email: string): Observable<any> {

    return this.http.get<any>(`https://tickethunt.stackroute.io/bookings/${eventId}/${email}`)
  }

}
