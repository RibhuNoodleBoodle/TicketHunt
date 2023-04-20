import { Component, HostListener } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { OrderService } from '../service/order.service';
import { RouteService } from '../service/route.service';
import { ActivatedRoute } from '@angular/router';
import { BookingServiceService } from '../service/booking.service';
import { Seats } from '../model/bookings';
import { concatMap } from 'rxjs/operators';
import { from } from 'rxjs';
import { NgxSpinnerService } from 'ngx-spinner';

declare var Razorpay: any;

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
})
export class PaymentComponent {

  emailId: any = sessionStorage.getItem('emailId');

  paymentForm = this.formBuilder.group({
    name: ['', Validators.required],
    email: ['', Validators.required],
    phoneNumber: ['', Validators.required],
    amount: [0, Validators.required]
  })

  get name() {
    return this.paymentForm.get('name');
  }

  get email() {
    return this.paymentForm.get('email');
  }

  get phoneNumber() {
    return this.paymentForm.get('phoneNumber');
  }

  get amount() {
    return this.paymentForm.get('amount');
  }

  // form: any = {
  //   name1: this.name?.value,
  //   email1: this.email?.value,
  //   phoneNumber1: this.phoneNumber?.value,
  //   amount1: this.amount?.value
  // };


  constructor(private http: HttpClient,
    private orderService: OrderService,
    private routeService: RouteService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private bookseat: BookingServiceService,
    private spinner: NgxSpinnerService) {

  }

  showName = "";
  selectedSeats = "";
  totalPrice = 0;
  date = "";
  id = "";


  ngOnInit() {
    this.showName = this.route.snapshot.queryParams['showName'];
    this.selectedSeats = this.route.snapshot.queryParams['seats'];
    this.totalPrice = this.route.snapshot.queryParams['price'];
    this.date = this.route.snapshot.queryParams['date'];
    this.id = this.route.snapshot.queryParams['id'];

    console.log(this.date)


    this.paymentForm.controls['amount'].setValue(this.totalPrice)

  }

  paymentId: string = '';
  error: string = '';
  loading = false;


  options = {
    "key": "",
    "amount": "",
    "name": "Ticket Hunt",
    "description": "Ticket Booking",
    "image": "https://www.javachinna.com/wp-content/uploads/2020/02/android-chrome-512x512-1.png",
    "order_id": "",
    "handler": function (response: any) {
      var event = new CustomEvent("payment.success",
        {
          detail: response,
          bubbles: true,
          cancelable: true
        }
      );
      window.dispatchEvent(event);
    }
    ,
    "prefill": {
      "name": "",
      "email": "",
      "contact": ""
    },
    "notes": {
      "address": ""
    },
    "theme": {
      "color": "#3399cc"
    }
  };

  onSubmit(): void {
    // console.log(this.amount?.value)
    // console.log(this.form.amount1.value)
    this.paymentId = '';
    this.error = '';
    this.orderService.createOrder(this.name?.value!, this.email?.value!, this.phoneNumber?.value!, this.amount?.value).subscribe(
      data => {
        this.options.key = data.secretId;
        this.options.order_id = data.razorpayOrderId;
        this.options.amount = data.applicationFee; //paise
        this.options.prefill.name = this.name?.value!;;
        this.options.prefill.email = this.email?.value!;
        this.options.prefill.contact = this.phoneNumber?.value!;

        if (data.pgName === 'razor2') {
          this.options.image = "";
          var rzp1 = new Razorpay(this.options);
          rzp1.open();
        } else {
          var rzp2 = new Razorpay(this.options);
          rzp2.open();
        }


        rzp1.on('payment.failed', function (response: { error: { code: any; description: any; source: any; step: any; reason: any; metadata: { order_id: any; payment_id: any; }; }; }) {
          // Todo - store this information in the server
          console.log(response);
          console.log(response.error.code);
          console.log(response.error.description);
          console.log(response.error.source);
          console.log(response.error.step);
          console.log(response.error.reason);
          console.log(response.error.metadata.order_id);
          console.log(response.error.metadata.payment_id);
          //this.error = response.error.reason;
        }
        );
      }
      ,
      (err: { error: { message: string; }; }) => {
        this.error = err.error.message;
      }
    );
  }



  @HostListener('window:payment.success', ['$event'])
  onPaymentSuccess(event: { detail: any; }): void {

    let index = 0
    console.log(this.selectedSeats.length);
    let price: any = sessionStorage.getItem("price");
    let transactionId = event.detail.razorpay_order_id;
    let message = `You have succesfully booked seats for the show "${sessionStorage.getItem('eventName')}" on ${sessionStorage.getItem('date')} at ${sessionStorage.getItem('venue')} and your booked seats are ${this.selectedSeats}...`
    this.spinner.show();
    this.loading = true;
    from(this.selectedSeats).pipe(
      concatMap(seat => {
        let seats = new Seats(seat, price, new Date, transactionId);
        return this.bookseat.bookSeats(seats, this.id, `${sessionStorage.getItem('emailId')}`);
      })
    ).subscribe((response: any) => {
      if (response.error) {
        alert(`Error: ${response.error}`);
      } else {
        console.log(response);
        index++

        if (index === this.selectedSeats.length)
          this.bookseat.sendEmail(this.email?.value, message).subscribe(
            response => {

              console.log(response);

            }
          )
        this.routeService.toConfirmation();
      }
    });
  }
}