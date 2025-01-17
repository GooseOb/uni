import {Component} from '@angular/core';
import {User, UserServiceService} from "../../core/services/user-service.service";

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent {
  user: User | null;
  constructor(private userService: UserServiceService) {
    this.user = userService.getCurrentUser();
  }

}
