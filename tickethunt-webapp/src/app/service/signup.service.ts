import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root',
})
export class SignupService {
  constructor(private http: HttpClient) { }

  post(user: User) {
    return this.http.post('https://tickethunt.stackroute.io/userData/commonUser', user);
  }


  getUser(email: string): Observable<Array<User>> {
    return this.http.get<Array<User>>(
      'https://tickethunt.stackroute.io/userData/email/' + email);
  }


  updateUser(email: any, user: any) {
    return this.http.put('https://tickethunt.stackroute.io/userData/update/' + email, user);
  }
}
