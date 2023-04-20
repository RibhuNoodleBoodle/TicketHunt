import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookingServiceService } from '../service/booking.service';
import { DataService } from '../service/data.service';

@Component({
  selector: 'app-event-manager',
  templateUrl: './event-manager.component.html',
  styleUrls: ['./event-manager.component.css']
})
export class EventManagerComponent {

  constructor(private dataService: DataService, private route: ActivatedRoute, private bookingsService: BookingServiceService) {

  }
  dataSource: any
  displayedColumns = ['emailId', 'dateofbooking', 'seats', 'price'];


  id: any;
  /* retrieveImage:any; */
  eventDetails: any;

  eventName: any;
  date: any;
  time: any;
  venue: any;
  price: any;
  seatNumbers: any;


  ngOnInit() {
    this.id = this.route.snapshot.queryParams['eventId'];
    this.getSeatsOfEvent(`${this.id}`)
    this.getEventData()
  }

  getEventData() {
    this.dataService.getEvent1(`${this.id}`).subscribe(response => {
      console.log(response)
      this.eventDetails = response

      console.log(this.eventDetails);


      this.eventName = this.eventDetails.eventName;
      this.date = this.eventDetails.date;
      this.time = this.eventDetails.time;
      this.venue = this.eventDetails.venue;
      this.price = this.eventDetails.price;


      console.log(this.eventDetails.price);

      /*      this.retrieveImage = 'data:image/png;base64,' + this.eventDetails.image
           console.log(this.retrieveImage); */

    })

  }
  getSeatsOfEvent(id: string) {
    this.bookingsService
      .getParticularBookings(id)
      .subscribe((booking: any) => {
        this.dataSource = booking.filter((booking: any) => booking.seatList.length > 0);
        this.seatNumbers = this.dataSource.map((booking: any) => booking.seatList);

        console.log(this.seatNumbers);



      });
  }
  totalPrice: any;
  getTotalPrice(eventId: string, email: string, price: number): number {

    const filteredData = this.dataSource.filter((booking: any) => booking.eventId === eventId && booking.email === email);
    if (filteredData.length > 0) {
      this.totalPrice = filteredData[0].seatList.length * price;
    }
    return this.totalPrice;
  }









}
