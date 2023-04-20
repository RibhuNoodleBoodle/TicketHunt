import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ThemePalette } from '@angular/material/core';

import { formatDate } from '@angular/common';
import { EventData } from 'src/app/model/event.model';
import { FileHandle } from 'src/app/model/file-handle.model';
import { DomSanitizer } from '@angular/platform-browser';
import { EventService } from 'src/app/service/event.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';


interface HTMLInputEvent extends Event {
  target: HTMLInputElement & EventTarget;
}

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})

export class CreateComponent implements OnInit {

  random: any = Math.floor(Math.random() * 1000)
  constructor(private _formBuilder: FormBuilder, private sanitizer: DomSanitizer, private eventService: EventService, private route: Router) {
    this.eventId?.setValue(this.random)
  }

  emailId: any = sessionStorage.getItem('emailId');
  ngOnInit(): void {
  }

  // Form Data and Validations
  basic = this._formBuilder.group({
    eventId: [''],
    eventName: ['', Validators.required],
    email: ['', Validators.required],
    date: ['', Validators.required],
    time: ['', Validators.required],
    venue: ['', Validators.required],
  });
  secondry = this._formBuilder.group({
    image: ['', Validators.required],
    organizerName: ['', Validators.required],
    seats: ['', Validators.required],
    eventType: ['', Validators.required],
    description: ['', Validators.required],
    price: ['', Validators.required],
    rating: ['', Validators.required]
  });
  isLinear = false;
  get eventId() {
    return this.basic.get('eventId')
  }

  // Toggler 
  // slideToggler() {
  //   const slideO = document.getElementById('img-one');
  //   if (slideO?.classList.contains('slide-active')) {
  //     slideO?.classList.remove('slide-active')
  //     slideO?.classList.add('slide-inactive')
  //   } else {
  //     slideO?.classList.remove('slide-inactive')
  //     slideO?.classList.add('slide-active')
  //   }
  // }

  // Default placeholder date

  // Current Date Placeholder
  defaultDate = new Date();
  fileHandler: FileHandle[] = [];
  file1: any;
  files: any[] = [];
  totalSeats = 50;

  // Method to be called when form is submitted
  save(basic: FormGroup, secondry: FormGroup) {
    setTimeout(() => {

      console.log(this.eventId?.value)
      // Converted this data to event type data
      let eventData: EventData = {
        eventId: this.eventId?.value,
        email: basic.get('email')?.value,
        eventName: basic.get('eventName')?.value,
        date: formatDate(basic.get('date')?.value, 'dd-MM-yyyy', 'en-US'),
        time: basic.get('time')?.value,
        venue: basic.get('venue')?.value,
        organizerName: secondry.get('organizerName')?.value,
        totalSeats: secondry.get('seats')?.value,
        eventType: secondry.get('eventType')?.value,
        description: secondry.get('description')?.value,
        price: secondry.get('price')?.value,
        rating: secondry.get('rating')?.value

      }
      console.log(eventData.eventId)
      // Use this data to save into database
      this.eventService.post(eventData, this.files[0]).subscribe(data =>
        console.log(data))
      this.route.navigateByUrl("event/view")


    }, 5000)
    Swal.fire({
      title: 'Event Added!!!',
      showClass: {
        popup: 'animate__animated animate__fadeInDown'
      },
      hideClass: {
        popup: 'animate__animated animate__fadeOutUp'
      }
    })


  }

  // prepareFormData(data: EventData): FormData {
  //   const formData = new FormData;

  //   formData.append('event', new Blob([JSON.stringify(data)], { type: 'application/json' }));
  //   if (data.image?.file) {
  //     formData.append('imageFile', data.image?.file, data.image?.file.name)
  //   }
  //   return formData;
  // }

  // onFileSelected(event: any) {
  //   if (event.target.files) {
  //     const file = event.target.files[0];
  //     const fileHandle: FileHandle = {
  //       file: file,
  //       url: this.sanitizer.bypassSecurityTrustUrl(
  //         window.URL.createObjectURL(file)
  //       )
  //     }
  //     this.fileHandler.push(fileHandle);
  //   }
  // }


  // document: any.getElementById('choose-file').onchange = function (e? : HTMLInputEvent ){
  //   let files:any  = e?.target.files[0];
  // }


  onFileChanged(files: any) {

    this.prepareFilesList(files);
  }

  prepareFilesList(files: Array<any>) {
    for (const item of files) {
      item.progress = 0;
      this.files.push(item);
    }
    this.uploadFilesSimulator(0);
  }

  uploadFilesSimulator(index: number) {
    setTimeout(() => {
      if (index === this.files.length) {
        return;
      } else {
        const progressInterval = setInterval(() => {
          if (this.files[index].progress === 100) {
            clearInterval(progressInterval);
            this.uploadFilesSimulator(index + 1);
          } else {
            this.files[index].progress += 5;
          }
        }, 200);
      }
    }, 1000);
  }

  // Property for mat-ti
  /** Override the ante meridiem abbreviation. */
  @Input() anteMeridiemAbbreviation = 'am';

  /** Override the post meridiem abbreviation. */
  @Input() postMeridiemAbbreviation = 'pm';

  /* Sets the clock mode, 12-hour or 24-hour clocks are supported. */
  @Input() mode: '12h' | '24h' = '24h';

  /* Set the color of the timepicker control */
  @Input() color: ThemePalette = 'primary';
}
