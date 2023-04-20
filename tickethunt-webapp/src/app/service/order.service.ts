import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  createOrder(name: any, email1: any, phoneNumber: any, amount: any): Observable<any> {
    return this.http.post("https://tickethunt.stackroute.io/payment/createOrder", {
      customerName: name,
      email: email1,
      phoneNumber: phoneNumber,
      amount: amount
    }, httpOptions);
  }
}