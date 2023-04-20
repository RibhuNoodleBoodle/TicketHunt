import { Component } from '@angular/core';
import { FormBuilder, ValidationErrors, ValidatorFn, Validators, AbstractControl, FormGroup } from '@angular/forms';
import { User } from 'src/app/model/user.model';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatChipEditedEvent, MatChipInputEvent } from '@angular/material/chips';
import { Interest } from 'src/app/model/interest.model';
import { SignupService } from 'src/app/service/signup.service';
import { Route, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  //Constructor
  constructor(private fb: FormBuilder, private signupService: SignupService, private router: Router) { }

  // Basic Information 
  customer = this.fb.group({
    name: ['', Validators.required],
    city: ['', Validators.required],
    phone: ['',
      Validators.compose([
        Validators.required,
        Validators.pattern('(9|8|7)[0-9]{9}'),
      ])],
    email: ['', Validators.compose([
      Validators.required,
      Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$'),
    ])],
    password: ['', Validators.compose([
      Validators.required,
      Validators.pattern(
        '^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\W]{8,63}$'
      ),
    ])],
    cnfPassword: ['', Validators.required]
  }, { validator: verifypwd('password', 'cnfPassword') });

  // Second form Data 
  interestData = this.fb.group({
    interest: ['', Validators.required],
    role: ['', Validators.required],
  });

  isEditable = false;

  // Once form is subbmited below method is called
  saveUser(customer: FormGroup, interest: FormGroup) {

    // Saving all data to user
    const userData: User = {
      name: customer.get('name')?.value,
      email: customer.get('email')?.value,
      password: customer.get('password')?.value,
      city: customer.get('city')?.value,
      phone: customer.get('phone')?.value,
      interest: this.interests,
      role: interest.get('role')?.value
    }
    // Logging data for testing - Use userData to submit data 
    this.signupService.post(userData).subscribe(next => {
      // alert('saved')
      Swal.fire({
        // position: 'top-end',
        icon: 'success',
        title: 'You have succesfully Registered!!',
        showConfirmButton: false,
        timer: 1500
      })
      this.router.navigateByUrl("login");
    })

  }

  // Chip Functionality
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

// Out function to check password and confirm password is same or different 

export function verifypwd(pass: string, cnfPass: string): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const password = control.get(pass)?.value;
    const cnfPassword = control.get(cnfPass)?.value;
    if (password != cnfPassword) {
      const err = { 'noMatch': true };
      control.get(cnfPass)?.setErrors(err);
      return err;
    }
    return null;
  }
}