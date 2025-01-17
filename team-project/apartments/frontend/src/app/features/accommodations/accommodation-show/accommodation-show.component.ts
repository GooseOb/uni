import {Component} from '@angular/core';
import {Accommodation, AccommodationServiceService} from "../../../core/services/accommodation-service.service";
import {ActivatedRoute} from "@angular/router";
import {Booking, BookingsService} from "../../../core/services/bookings.service";
import {User, UserServiceService} from "../../../core/services/user-service.service";
import {AppModule} from "../../../app.module";
import {formatNumber} from "@angular/common";

@Component({
  selector: 'app-accommodation-show',
  templateUrl: './accommodation-show.component.html',
  styleUrls: ['./accommodation-show.component.css']
})
export class AccommodationShowComponent {
  accommodation: Accommodation | any
  dateStart: string | any
  dateEnd: string | any
  user: User
  bookings: Booking[] = []
  error: boolean | null = null;
  success: boolean = false;

  constructor(
    public accommodationService: AccommodationServiceService,
    private route: ActivatedRoute,
    public bookingsService: BookingsService,
    private userService: UserServiceService) {
    this.user = userService.getCurrentUser()!;
    this.route.queryParams.subscribe(
      params => {
        if (params["a"]) {
          accommodationService.getAccommodation(params["a"]).subscribe(accommodation => {
            this.accommodation = accommodation;
            accommodationService.getPresentAccommodationsBookings(this.accommodation).subscribe(bookings => {
              this.bookings = bookings
            });
          });
        }
        if (params["s"] && params["s"] == 1) {
          this.success = true;
          this.refreshErrorAndSuccess();
        }
      }
    )

  }

  addBooking() {
    const booking = {
      dateStart: Date.parse(this.dateStart),
      dateEnd: Date.parse(this.dateEnd),
      accommodation: this.accommodation
    };
    const observable = this.bookingsService.addBooking(booking);
    this.error = observable === null;
    if (!this.error) {
      observable!
        .subscribe({
          next: (response) => {
            this.bookings.push(response as Booking)
          },
          error: () => {
            this.error = true;
          }
        })
    }
  }

  deleteBooking(booking: Booking) {
    this.bookingsService.deleteBooking(booking)
      .subscribe(() => {
        this.bookings = this.bookings!.filter(item => item !== booking)
      })
  }

  refreshErrorAndSuccess() {
    if (this.error !== null) {
      this.success = !this.error;
    }
    if (this.error === false || this.success) {
      this.accommodationService.getPresentAccommodationsBookings(this.accommodation).subscribe(bookings => {
        this.bookings = bookings;
      });
    }
  }

  openModal(path: string) {
    const modal = document.getElementById("modal");
    const modalImg = document.getElementById("img");
    if (modal && modalImg) {
      modal.style.display = "block";
      modalImg.setAttribute("src", path);
    }
  }

  closeModal() {
    const modal = document.getElementById("modal");
    if (modal) {
      modal.style.display = "none";
    }
  }

  protected readonly AppModule = AppModule;
  protected readonly formatNumber = formatNumber;
  protected readonly document = document;
}
