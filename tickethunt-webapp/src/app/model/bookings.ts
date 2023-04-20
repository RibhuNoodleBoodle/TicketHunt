export interface Bookings {
    email: any,
    eventName: any,
    eventId: any,
    venue: any,
    totalSeats: any,
    time: any,
    date: any

}


export class Seats {
    public seatNumber: String;
    public price: number;
    public dateOfBooking: Date;
    public transactionId: String;


    constructor(seatNum: String, price: number,
        dateOfBooking: Date, transactionId: String) {
        this.seatNumber = seatNum;
        this.dateOfBooking = dateOfBooking;
        this.price = price;
        this.transactionId = transactionId;
    }
}