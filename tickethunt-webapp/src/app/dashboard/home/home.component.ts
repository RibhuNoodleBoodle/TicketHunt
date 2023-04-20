import { ThisReceiver } from '@angular/compiler';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventData } from 'src/app/model/event';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { DataService } from 'src/app/service/data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(
    private dataService: DataService,
    private route: Router,
    private authService: AuthenticationService
  ) {}
  @Input()
  events2: any;
  events1: any;

  movies: EventData[] = [];
  recommended: EventData[] = [];
  carry: EventData[] = [];
  event: any = {};
  image: string = '';

  i: number = 1;

  isLoggedIn: boolean = false;

  ngOnInit(): void {
    this.viewEvent();
    this.viewMovie();
    this.changeSlider();
    this.verifyUser();
  }

  viewMovie() {
    this.dataService.getAllEvents3().subscribe((response) => {
      this.events1 = response;
    });
    this.dataService.getAllEvent().subscribe((value) => {
      value.forEach((element) => {
        this.movies.push(element);
      });
      this.carry.push(this.movies[0]);
      this.recommended = shuffle(this.movies);
    });
  }

  viewEvent() {
    this.dataService.getAllEvents2().subscribe((response) => {
      this.events2 = response;
    });
  }
  navigateToMovieDetail(eventId: any) {
    this.route.navigate(['detail', eventId]);
  }
  verifyUser() {
    let email = sessionStorage.getItem('emailId')
    setInterval(() => {
      if (email) {
        this.isLoggedIn = true;
      }else {
        this.isLoggedIn = false;
      }
      
    }, 1000);
  }
  changeSlider() {
    setInterval(() => {
      let id = this.movies[this.i]?.eventId;
      if (this.i < this.movies.length) {
        this.carry.pop();
        this.carry.push(this.movies[this.i]);
        this.i++;
      } else {
        this.i = 0;
        this.carry.pop();
        this.carry.push(this.movies[0]);
      }
    }, 7000);
  }
}

export function shuffle<EventData>(array: EventData[]): EventData[] {
  let currentIndex = array.length,
    randomIndex;

  // While there remain elements to shuffle.
  while (currentIndex != 0) {
    // Pick a remaining element.
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex--;

    // And swap it with the current element.
    [array[currentIndex], array[randomIndex]] = [
      array[randomIndex],
      array[currentIndex],
    ];
  }

  return array;
};