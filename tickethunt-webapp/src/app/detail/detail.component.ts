import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventData } from '../model/event';
import { DataService } from '../service/data.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css'],
})
export class DetailComponent {
  constructor(
    private activatedRoute: ActivatedRoute,
    private dataService: DataService,
    private router: Router
  ) { }
  event: any = {};

  @Input()
  events: any;

  movies: EventData[] = [];

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      let id = params.get('id');
      this.dataService.getEvent1(id).subscribe((data) => {
        this.event = data;
        sessionStorage.setItem('eventName', this.event.eventName);
        sessionStorage.setItem('eventId', this.event.eventId);
        sessionStorage.setItem('venue', this.event.venue);
        sessionStorage.setItem('date', this.event.date);
        sessionStorage.setItem('totalSeats', this.event.totalSeats);
        sessionStorage.setItem('time', this.event.time);
        sessionStorage.setItem('price', this.event.price);
      });
      this.getRecomendation(id);
    });
  }

  getRecomendation(id: any) {
    this.dataService.getAllEvent().subscribe((value) => {
      this.movies = value.filter((x) => x.eventId != id);
    });
  }

  navigateToMovieDetail(eventId: any) {
    this.router.navigate(['detail', eventId]);
  }

  book() {
    this.router.navigate(['booking/' + this.event.eventId]);
  }
}
