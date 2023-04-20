import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/service/authentication.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  constructor(
    private authService: AuthenticationService,
    private fb: FormBuilder,
    private router: Router,
    private http:HttpClient
  ) {}

  ngOnInit() {
    this.verifyLogin();
  }

  user = this.fb.group({
    email: [
      '',
      Validators.compose([
        Validators.required,
        Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$'),
      ]),
    ],
    password: ['', Validators.required],
  });
  responseData: any;

  login(user: FormGroup) {
    this.authService.login(this.user.value).subscribe({
      next: (response) => {
        this.responseData = response;
        sessionStorage.setItem('jwtkey', this.responseData.token);
        sessionStorage.setItem('userEmail', this.responseData.userEmail);
        sessionStorage.setItem('role', this.responseData.role);
        let email = this.responseData.userEmail.split('=')[1].split(',')[0];
        sessionStorage.setItem('emailId', email);
        // alert("Welcome to our app, user!");
        Swal.fire({
          // position: 'top-end',
          icon: 'success',
          title: 'You have succesfully logged in!!',
          showConfirmButton: false,
          timer: 1500,
        });

        if (this.responseData.role === 'event') {
          this.authService.isRole = 'event';
        }
        this.authService.isUserLogedIn = true;
        this.router.navigateByUrl('/home');
      },

      error: (err) => {
        console.log(err);
        this.authService.isUserLogedIn = false;
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Something went wrong!',
        });
      },
    });
  }
  verifyLogin() {
    let email = sessionStorage.getItem('userEmail');
    if (email) {
      this.router.navigate(['/home']);
      this.authService.isUserLogedIn = true;
    }
  }
}
