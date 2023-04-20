import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { Bookings } from '../model/bookings';
import { BookingServiceService } from '../service/booking.service';

@Component({
  selector: 'app-booking-history',
  templateUrl: './booking-history.component.html',
  styleUrls: ['./booking-history.component.css']
})
export class BookingHistoryComponent implements OnInit {

  constructor(private bookingsService: BookingServiceService) {

  }

  ngOnInit() {
    this.getData();

  }

  bookingData: any;
  seatNumbers: any;
  price: any;
  displayedColumns: string[] = ['eventName', 'venue', 'date', 'seatList', 'price', 'dateOfBooking'];

  getData() {

    this.bookingsService.bookingHistoryOfUser().subscribe(
      (response: any) => {

        this.bookingData = response.filter((booking: any) => booking.seatList.length > 0);
        console.log(response);
        this.seatNumbers = this.bookingData.map((booking: any) => booking.seatList.length);
        console.log(this.seatNumbers);


      }
    )
  }



  delete(seat: any, eventId: any) {

    Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: 'Successfully cancelled=> ' + seat,
      showConfirmButton: false,
      timer: 4000
    })
    // alert("successfully canceled seat =>" + seat)
    window.location.reload();
    this.bookingsService.cancelSeats(seat, eventId).subscribe(
      (response: any) => {

      }

    )
  }

}