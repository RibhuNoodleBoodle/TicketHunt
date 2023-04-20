import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Movie } from '../model/movie.model';
import { Bookings } from '../model/bookings';

import { BookingServiceService } from '../service/booking.service';
import { DataService } from '../service/data.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css'],
})
export class BookingComponent implements OnInit {
  public rows: Array<String> = [];
  public seats: Array<any> = [];
  public seatAvailable: Array<any> = [];
  booking!: FormGroup;
  price: any;

  currentDate: any = new Date();

  id: any;
  eventName = localStorage.getItem('eventName');
  constructor(
    private bookingsService: BookingServiceService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private dataService: DataService
  ) {
    this.booking = this.fb.group({
      seatNumber: new FormControl('', [Validators.required]),
      dateOfBooking: [this.currentDate],
      price: [this.price],
    });
  }

  email: any;
  newBooking: any

  ngOnInit() {
    this.route.paramMap.subscribe((param) => {
      this.id = param.get('id');
    });
    this.email = sessionStorage.getItem('emailId');
    /* this.loadData(); */
    // Hardcoded for now to create seat arrangement for the first time
    this.rows = ['A', 'B', 'C', 'D', 'E'];
    this.seats = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    this.getSeatsOfEvent(this.id);

    this.newBooking = {
      "email": this.email,
      "eventName": sessionStorage.getItem('eventName'),
      "eventId": sessionStorage.getItem('eventId'),
      "venue": sessionStorage.getItem('venue'),
      "totalSeats": sessionStorage.getItem('totalSeats'),
      "date": sessionStorage.getItem('date'),
      "time": sessionStorage.getItem('time'),
      "seatList": []

    }

  }

  movies: Movie[] = [];

  bookings: any;
  occupiedSeats: any[] = [];
  selectedSeats: any[] = [];

  bookingData: any;
  seatData: any[] = [];

  eventDate: any;

  getSeatsOfEvent(id: string) {
    this.bookingsService
      .getParticularBookings(id)
      .subscribe((response: any) => {
        this.bookings = response;
        console.log(response);
        this.updateOccupiedSeats();
        console.log(this.bookings)

      });
  }

  isSeatSelected(seatNumber: any) {
    return this.selectedSeats.includes(seatNumber);
  }

  updateOccupiedSeats(): void {


    for (const data of this.bookings) {
/*         this.eventDate = data.date
 */        for (const seat of data.seatList) {

        this.occupiedSeats.push(seat.seatNumber);
      }
    }
  }

  data: any;

  bookSeat() {
    /*     const logData = this.booking.value;
     */

    let cost = this.price * this.selectedSeats.length;

    this.bookingsService.findByEventIdAndEmail(this.id, this.email).subscribe(
      response => {
        this.data = response
        console.log(this.data);
        console.log(this.email);
        console.log(this.id);


        if (this.data === null) {
          this.bookingsService.addBookingForNewEmail(this.newBooking).subscribe(
            res => {
              console.log(res);

            }, error => {
              console.log(error);

            }
          )
          console.log(this.newBooking)
        }
      }
    )


    this.router.navigate(['/payment'], {
      queryParams: {
        showName: sessionStorage.getItem('eventName'),
        seats: this.selectedSeats, price: cost,
        date: this.eventDate,
        id: this.id
      }
    })


    this.booking.controls['price'].setValue(this.price);

    for (let seat of this.selectedSeats) {
      this.booking.controls['seatNumber'].setValue(seat);
      /*  alert('selected seats are=>' + seat); */
      /* this.bookingsService
        .bookSeats(this.booking.value, this.id)
        .subscribe((response: any) => {
          console.log(logData);
          window.location.reload();
        }); */
    }
  }

  public isSeatReserved(seatNum: string) {
    if (this.occupiedSeats) {
      return this.occupiedSeats.includes(seatNum);
    }
    return null;
  }

  public checkSeatStatus(seatNum: string): boolean {
    if (this.occupiedSeats.includes(seatNum)) {
      return true;
    }
    return false;
  }

  getSeatColor(seatNumber: string) {
    if (this.occupiedSeats.includes(seatNumber)) {

      return 'grey';

    }
    if (!this.selectedSeats.includes(seatNumber)) {

      return 'whitesmoke';
    }

    else {
      return "rgba(74, 158, 74, 0.582)";
    }
  }


  public seatSelect(seatNumber: string) {
    if (this.selectedSeats.includes(seatNumber)) {
      // If the seat is already selected, remove it from the array
      this.selectedSeats = this.selectedSeats.filter((s) => s !== seatNumber);
      this.booking.controls['seatNumber'].setValue(this.selectedSeats);
      //
    } else {
      this.selectedSeats.push(seatNumber);
      console.log(this.selectedSeats);

      if (this.occupiedSeats.includes(seatNumber)) {
        localStorage.setItem('seatNumber', seatNumber);
        this.selectedSeats = this.selectedSeats.filter((s) => s !== seatNumber);
      } else {
        /*  this.selectedSeats.push(seatNumber);  */

        this.booking.controls['seatNumber'].setValue(this.selectedSeats);
      }
    }
  }

  totalprice() {
    if (this.selectedSeats.length == 1) {
      this.price = sessionStorage.getItem("price");
    }
    let totalCost = this.price * this.selectedSeats.length;

    return totalCost;
  }

}