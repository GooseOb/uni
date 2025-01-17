import {Component, OnInit} from '@angular/core';
import {Accommodation, AccommodationServiceService} from "../../../core/services/accommodation-service.service";
import {AppModule} from "../../../app.module";
import {Booking, BookingsService} from "../../../core/services/bookings.service";
import {catchError, map, throwError} from "rxjs";
import {formatNumber} from "@angular/common";
import {User, UserServiceService} from "../../../core/services/user-service.service";

@Component({
  selector: 'app-list-host-accommodations',
  templateUrl: './list-host-accommodations.component.html',
  styleUrls: ['./list-host-accommodations.component.css']
})
export class ListHostAccommodationsComponent { //implements OnInit{
  accommodations: Accommodation[];
  user: User
  deletionErrorAccommodationName: undefined | string;

  constructor(
    private accommodationService: AccommodationServiceService,
    private userService: UserServiceService) {
    this.user = userService.getCurrentUser();
    this.accommodations = [];
    this.accommodationService.getHostAccommodations().subscribe((data: Accommodation[]) => {
      this.accommodations = data;
    });
  }

  // ngOnInit(): void {
  //   this.accommodationService.getHostAccommodations().subscribe((data: Accommodation[]) => {
  //     this.accommodations = data;
  //   });
  // }

  public deleteAccommodation(accommodation: Accommodation) {
    this.accommodationService.deleteAccommodation(accommodation)
      .subscribe({
        next: () => {
          this.accommodations = this.accommodations.filter(item => item.id !== accommodation.id);
        },
        error: () => {
          this.deletionErrorAccommodationName = accommodation.name;
        }
      })
  }

  public isBooked(accommodation: Accommodation) {
    return this.accommodationService.getPresentAccommodationsBookings(accommodation)
      .pipe(map(bookings => {
        return bookings.length != 0;
      }))
  }

  protected readonly AppModule = AppModule;
  protected readonly formatNumber = formatNumber;
}
