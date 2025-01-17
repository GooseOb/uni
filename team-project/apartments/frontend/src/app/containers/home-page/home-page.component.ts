import { Component } from '@angular/core';
import {User, UserServiceService} from "../../core/services/user-service.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})

export class HomePageComponent {
  user: User | null;

  constructor(private userService: UserServiceService) {
    this.user = userService.getCurrentUser();
  }

}
