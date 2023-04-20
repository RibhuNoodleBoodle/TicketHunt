import { ENTER, COMMA } from '@angular/cdk/keycodes';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatChipInputEvent, MatChipEditedEvent } from '@angular/material/chips';
import { Router } from '@angular/router';
import { SignupService } from 'src/app/service/signup.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  profile: any = [];

  editEnable: boolean = false;

  constructor(
    private signupService: SignupService,
    private router: Router,
    private fb: FormBuilder,
    private _snackBar: MatSnackBar
  ) { }
  ngOnInit(): void {
    let email = sessionStorage.getItem('emailId');
    if (email) {
      this.getUser(email);
    }
  }

  // Get user info when loggin

  user = this.fb.group({
    name: ['', Validators.required],
    city: ['', Validators.required],
    phone: ['', Validators.required],
    interest: ['', Validators.required],
  });

  getUser(email: string) {
    if (email != '') {
      this.signupService.getUser(email).subscribe(
        (x) => {
          this.profile = x;
        },
        (error) => {
          this.profile.push(error.error);
        }
      );
    } else {
      this.router.navigate(['/login']);
    }
  }

  // Update functionality

  enableEdit() {
    this.user.controls['name'].setValue(this.profile.name);
    this.user.controls['city'].setValue(this.profile.city);
    this.user.controls['phone'].setValue(this.profile.phone);
    this.interests = [];
    this.profile.interest.forEach((value: string) => {
      this.interests.push(value);
    });
    this.editEnable = true;
  }

  updateProfile(user: FormGroup) {
    let userUpdate: Profile = {
      name: user.controls['name'].value,
      email: this.profile.email,
      city: user.controls['city'].value,
      phone: user.controls['phone'].value,
      interest: this.interests,
    };
    // Use this data to send update
    this.signupService.updateUser(this.profile.email, userUpdate).subscribe((next) => {
      this.profile = userUpdate;
      this.editEnable = false;
      this.openSnackBar('Profile Updated', 'Success');
    }, (error) => console.log(error))
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

  // Chip
  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;

  interests: String[] = [];

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.interests.push(value);
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  remove(value: String): void {
    const index = this.interests.indexOf(value);

    if (index >= 0) {
      this.interests.splice(index, 1);
    }
  }

  edit(fruit: String, event: MatChipEditedEvent) {
    const value = event.value.trim();

    // Remove fruit if it no longer has a name
    if (!value) {
      this.remove(fruit);
      return;
    }

    // Edit existing fruit
    const index = this.interests.indexOf(fruit);
    if (index > 0) {
      this.interests[index] = value;
    }
  }
}

export interface Profile {
  name?: String;
  email?: String;
  city?: String;
  phone?: number;
  interest?: String[];
}
