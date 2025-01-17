import { Component } from '@angular/core';
import {User, UserServiceService} from "../core/services/user-service.service";
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  currentUser: User | null = null;

  constructor(private userService: UserServiceService,
              private router: Router) {
    userService.currentUser$.subscribe(user => {
      this.currentUser = user;
    })
  }

  logout(){
    this.userService.clearCurrentUser();
    this.router.navigate(["/login"])
  }
}
