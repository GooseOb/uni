import { Component } from '@angular/core';
import {User, UserServiceService} from "../../core/services/user-service.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-dashboard-page',
  templateUrl: './dashboard-page.component.html',
  styleUrls: ['./dashboard-page.component.css']
})

export class DashboardPageComponent {
  user: User | null = null;
  action: string | undefined;

  constructor(
    private userService: UserServiceService,
    private route: ActivatedRoute) {
    this.user = this.userService.getCurrentUser()
    this.route.queryParams.subscribe(params => {
      if(params["a"]) {
        this.action = params["a"].toString();
      } else {
        this.action = "settings";
      }
    });
  }
}
