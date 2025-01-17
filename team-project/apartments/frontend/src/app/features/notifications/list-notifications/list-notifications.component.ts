import {Component, OnInit} from '@angular/core';
import {formatNumber} from "@angular/common";
import {Notification, NotificationsService} from "../../../core/services/notifications.service";
import {AppModule} from "../../../app.module";

@Component({
  selector: 'app-list-notifications',
  templateUrl: './list-notifications.component.html',
  styleUrls: ['./list-notifications.component.css']
})
export class ListNotificationsComponent implements OnInit{
  notifications: Notification[]

  constructor(public notificationsService: NotificationsService) {
    this.notifications = [];
  }

  ngOnInit(): void {
    this.notificationsService.getUserNotifications().subscribe((data: Notification[]) => {
      this.notifications = data;
    });
  }

  acceptNotification(notification: Notification) {
    this.notificationsService.acceptNotification(notification)
      .subscribe(() => {
        this.notifications = this.notifications.filter(item => item !== notification)
      })
  }

  rejectNotification(notification: Notification) {
    this.notificationsService.rejectNotification(notification)
      .subscribe(() => {
        this.notifications = this.notifications.filter(item => item !== notification)
      })
  }


  protected readonly formatNumber = formatNumber;
  protected readonly AppModule = AppModule;
}
