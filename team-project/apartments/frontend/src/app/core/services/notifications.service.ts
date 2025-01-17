import {Injectable} from '@angular/core';
import {Booking} from "./bookings.service";
import {User, UserServiceService} from "./user-service.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {AppModule} from "../../app.module";

export interface Notification {
  id: number;
  type: number;
  dateAdded: Date;
  targetBooking: Booking;
}

@Injectable({
  providedIn: 'root'
})
export class NotificationsService {
  currentUser: User

  constructor(private userService: UserServiceService,
              private http: HttpClient) {
    this.currentUser = userService.getCurrentUser()
  }

  public getUserNotifications(): Observable<Notification[]> {
    let notifications: Notification[] | null;
    notifications = null;
    return this.http.get<Notification[]>(
      AppModule.API_ADDRESS + "notifications",
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Basic ' + btoa(this.currentUser.username + ":" + this.currentUser.notHashedPassword)
        })
      })
      .pipe(
        map((response: Notification[]) => {
          return response;
        })
      );
  }

  public acceptNotification(notification: Notification) {
    return this.http.post(
      AppModule.API_ADDRESS + "notifications/accept",
      notification,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Basic ' + btoa(this.currentUser.username + ":" + this.currentUser.notHashedPassword)
        })
      })
  }

  public rejectNotification(notification: Notification) {
    return this.http.post(
      AppModule.API_ADDRESS + "notifications/reject",
      notification,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Basic ' + btoa(this.currentUser.username + ":" + this.currentUser.notHashedPassword)
        })
      })
  }

}
