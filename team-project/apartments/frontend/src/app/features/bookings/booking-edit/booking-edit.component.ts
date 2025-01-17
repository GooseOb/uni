import { Component } from '@angular/core';
import {AppModule} from "../../../app.module";
import {Booking, BookingsService} from "../../../core/services/bookings.service";
import {ActivatedRoute} from "@angular/router";
import {formatNumber} from "@angular/common";

@Component({
  selector: 'app-booking-edit',
  templateUrl: './booking-edit.component.html',
  styleUrls: ['./booking-edit.component.css']
})
export class BookingEditComponent {
  booking: Booking | undefined;
  change: string | undefined;

  constructor(
    private bookingsService: BookingsService,
    private route: ActivatedRoute
  ) {
    this.route.queryParams.subscribe(params => {
      if(params["a"]){
        this.bookingsService.getBooking(params["a"]).subscribe(booking => {
          this.booking = booking;
          console.log(this.booking);
        });
      }
    })
  }

  onSubmit(){
    this.bookingsService.updateBooking(this.booking);
    this.change = "";
  }

  protected readonly AppModule = AppModule;
  protected readonly formatNumber = formatNumber;
}
