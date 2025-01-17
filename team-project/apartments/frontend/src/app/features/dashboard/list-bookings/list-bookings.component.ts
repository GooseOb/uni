import {Component, OnInit} from '@angular/core';
import {Booking, BookingsService} from "../../../core/services/bookings.service";
import {formatNumber} from "@angular/common";
import {User, UserServiceService} from "../../../core/services/user-service.service";

@Component({
  selector: 'app-list-bookings',
  templateUrl: './list-bookings.component.html',
  styleUrls: ['./list-bookings.component.css']
})
export class ListBookingsComponent implements OnInit{
  bookings: Booking[];
  user: User;

  constructor(private bookingsService: BookingsService,
              private userService: UserServiceService) {
    this.user = userService.getCurrentUser();
    this.bookings = [];
  }

  ngOnInit(): void {
    this.bookingsService.getUserBookings().subscribe((data: Booking[]) => {
      this.bookings = data;
      console.log(this.bookings);
    });
  }

  deleteBooking(booking: Booking) {
    this.bookingsService.deleteBooking(booking).subscribe(() => {
      this.bookings = this.bookings.filter(item => item !== booking)
    })
  }

  protected readonly formatNumber = formatNumber;
}
