import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventData } from 'src/app/model/event.model';
// import { EVENTS } from 'src/app/model/events';
import Swal from 'sweetalert2'
import { DataService } from 'src/app/service/data.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-event-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewEventComponent implements OnInit {

  constructor(private dataService: DataService, private router: Router, private activatedRoute: ActivatedRoute) { }

  eventDetails: any = {};
  retrieveImage: any;
  base64Data: any;

  @Input()
  events: any;

  email: any = sessionStorage.getItem('emailId')

  event: any = {}

  ngOnInit(): void {
    this.getEventByEmail();
  }

  getEventByEmail() {
    console.log(this.email)
    this.dataService.getEventByEmail(`${this.email}`).subscribe(response => {
      console.log(response)
      this.eventDetails = response
      this.retrieveImage = 'data:image/png;base64,' + this.eventDetails.image
    })



    //console.log(response.length)

    //this.base64Data = this.eventDetails.image;
    //console.log(this.base64Data)
    //this.retrieveImage = 'data:image/png;base64,' + this.eventDetails.image
    //console.log(this.retrieveImage)
  }

  redirectToEventManager(eventId: any) {
    this.router.navigate([`eventmanager/${eventId}`],
      {
        queryParams: {
          eventId: eventId
        }
      });
  }


  // getAllEvents() {
  //   this.dataService.getEvent1(12).subscribe(response => {
  //     console.log(response)
  //     this.eventDetails = response
  //     //this.base64Data = this.eventDetails.image;
  //     //console.log(this.base64Data)
  //     this.retrieveImage = 'data:image/png;base64,' + this.eventDetails.image
  //     //console.log(this.retrieveImage)

  //   })
  // }

  editNote() {
    this.dataService.editPost(this.event).subscribe(data => {
      this.event = data;
    })
  }

  deleteNote(eventId: any) {

    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.dataService.deletePost(eventId).subscribe(data => {
          console.log("test delete")
          // window.location.reload();
        });
        Swal.fire(
          'Deleted!',
          'Your file has been deleted.',
          'success'
        )
        setTimeout(() => {
          // location.reload();
        }, 4000)

      }
    })

  }



}
