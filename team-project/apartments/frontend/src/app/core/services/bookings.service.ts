import {Injectable} from '@angular/core';
import {User, UserServiceService} from "./user-service.service";
import {Accommodation} from "./accommodation-service.service";
import {map, Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AppModule} from "../../app.module";
import {formatDate} from "@angular/common";

export interface Booking {
  id: number,
  user: User,
  accommodation: Accommodation,
  dateStart: string,
  dateEnd: string,
  dateBooked: Date,
  price: number,
  accepted: boolean,
  rejected: boolean
}

@Injectable({
  providedIn: 'root'
})
export class BookingsService {
  currentUser: User

  constructor(private userService: UserServiceService,
              private http: HttpClient) {
    this.currentUser = this.userService.getCurrentUser()!
  }

  public addBooking(booking: Booking | any) {
    booking.user = this.currentUser;
    if (!booking.dateStart || !booking.dateEnd) {
      return null;
    }
    booking.dateStart = formatDate(booking.dateStart, "yyyy-MM-dd", "en-US") + "T00:00:00";
    booking.dateEnd = formatDate(booking.dateEnd, "yyyy-MM-dd", "en-US") + "T00:00:00";
    return this.http.post(
      AppModule.API_ADDRESS + "bookings",
      booking,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Basic ' + btoa(this.currentUser.username + ":" + this.currentUser.notHashedPassword)
        })
      })
  }

  public updateBooking(booking: Booking | any): Booking | null {
    booking.user = this.currentUser;
    booking.dateStart = formatDate(booking.dateStart, "yyyy-MM-dd", "en-US") + "T00:00:00";
    booking.dateEnd = formatDate(booking.dateEnd, "yyyy-MM-dd", "en-US") + "T00:00:00";
    this.http.put(
      AppModule.API_ADDRESS + "bookings",
      booking,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Basic ' + btoa(this.currentUser.username + ":" + this.currentUser.notHashedPassword)
        })
      })
      .subscribe(response => {
        console.log(response);
        return response;
      })
    return null;
  }

  public deleteBooking(booking: Booking) {
    booking.user = this.currentUser;
    return this.http.delete(
      AppModule.API_ADDRESS + "bookings",
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Basic ' + btoa(this.currentUser.username + ":" + this.currentUser.notHashedPassword)
        }),
        body: booking
      })
  }

  public getUserBookings(): Observable<Booking[]> {
    let bookings: Booking[] | null;
    bookings = null;
    return this.http.get<Booking[]>(
      AppModule.API_ADDRESS + "bookings/users/" + this.currentUser.id,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Basic ' + btoa(this.currentUser.username + ":" + this.currentUser.notHashedPassword)
        })
      })
      .pipe(
        map((response: Booking[]) => {
          return response;
        })
      );
  }

  public getBooking(id: number): Observable<Booking> {
    let bookings: Booking[] | null;
    bookings = null;
    return this.http.get<Booking>(
      AppModule.API_ADDRESS + "bookings/" + id,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Basic ' + btoa(this.currentUser.username + ":" + this.currentUser.notHashedPassword)
        })
      })
      .pipe(
        map((response: Booking) => {
          return response;
        })
      );
  }

}
