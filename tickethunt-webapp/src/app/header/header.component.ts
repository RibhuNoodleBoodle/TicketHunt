import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventData } from '../model/event';
import { AuthenticationService } from '../service/authentication.service';
import { DataService } from '../service/data.service';
import { LocationService } from '../service/location.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  constructor(
    private authService: AuthenticationService,
    private router: Router,
    private locationService: LocationService,
    private dataService: DataService
  ) { }

  ngOnInit(): void {
    this.toggleTimeout();
    this.validateLogin();
    this.getLocation();
    // this.role = sessionStorage.getItem('role');
    // console.log(this.authService.isUserLogedIn)
  }

  isLoggedIn: boolean = false;
  // role: any = sessionStorage.getItem('role');
  city: string = '';
  role: string = '';
  movies: EventData[] = [];

  // Cities
  cities: string[] = [
    'Mumbai',
    'Pune',
    'Delhi',
    'Banglore',
    'Agra',
    'Noida',
    'Dehradun',
    'Chennie',
  ];
  citiesFilter: string[] = [];
  toggle() {
    const menu = document.getElementById('event-menu');
    if (menu?.classList.contains('menu-active')) {
      menu?.classList.remove('menu-active');
      menu.classList.add('menu-inactive');
    } else {
      menu?.classList.remove('menu-inactive');
      menu?.classList.add('menu-active');
    }
  }

  toggleTimeout() {
    const menu = document.getElementById('event-menu');
    setInterval(() => {
      if (menu?.classList.contains('menu-active')) {
        menu?.classList.remove('menu-active');
        menu.classList.add('menu-inactive');
      }
    }, 5000);
  }

  validateLogin() {
    setInterval(() => {
      let email = sessionStorage.getItem('emailId');
      let role = sessionStorage.getItem('role');
      if (this.authService.isUserLogedIn && role) {
        this.isLoggedIn = this.authService.isUserLogedIn;
        this.role = role;
      } else if (email && role) {
        this.isLoggedIn = true;
        this.role = role;
      } else {
        this.isLoggedIn = false;
        this.role = '';
      }
    }, 1000);
  }

  logout() {
    alert('Confirm to LogOut');
    this.authService.isUserLogedIn = false;
    sessionStorage.removeItem('jwtkey');
    sessionStorage.removeItem('userEmail');
    sessionStorage.clear();
    this.router.navigateByUrl('/login');
  }

  getLocation() {
    navigator.geolocation.getCurrentPosition(
      (position: GeolocationPosition) => {
        this.locationService.getLocation(position).subscribe((city: any) => {
          this.city = city.results[0].components.state_district;
        });
      }
    );
  }

  getCity() {
    const value = (document.getElementById('city') as HTMLInputElement).value;
    this.citiesFilter = this.cities.filter((x) =>
      x.toLocaleLowerCase().startsWith(value.toLocaleLowerCase().toString())
    );
  }
  navigateToMovieDetail(eventId: any) {
    this.router.navigate(['detail', eventId]);
    (document.getElementById('search') as HTMLInputElement).value = '';
    this.removeAnimation();
  }
  search() {
    const input = (document.getElementById('search') as HTMLInputElement).value;
    const result = document.getElementById('results');
    const noItem = document.getElementById('no-item');
    const loader = document.getElementById('load');
    if (input != '') {
      this.dataService.getAllEvent().subscribe((value) => {
        this.movies = value.filter((x) =>
          x.eventName?.toLowerCase().startsWith(input.toLowerCase())
        );
        result?.classList.add('auto');
        // Loader related property
        if (this.movies.length <= 0) {
          loader?.style.setProperty('display', 'flex');
          noItem?.style.setProperty('display', 'none');
          setTimeout(() => {
            loader?.style.setProperty('display', 'none');
            noItem?.style.setProperty('display', 'block');
          }, 1000);
        }
      });
    } else {
      this.movies = [];
      this.animate();
      setTimeout(() => {
        result?.classList.remove('auto');
      }, 1000);
    }
  }
  animate() {
    const result = document.getElementById('results');
    const noItem = document.getElementById("no-item");
    const loader = document.getElementById('load');
    result?.classList.add('auto');
    setTimeout(() => {
      noItem?.style.setProperty('display', 'block');
      loader?.style.setProperty('display', 'none');
    }, 2000);
    result?.classList.remove('auto');
  }
  removeAnimation() {
    const result = document.getElementById('results');
    this.movies = [];
    result?.classList.remove('auto');
  }
}
