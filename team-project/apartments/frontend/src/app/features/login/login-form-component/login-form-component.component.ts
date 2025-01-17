import {Component} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserServiceService, User} from "../../../core/services/user-service.service";
import {AppModule} from "../../../app.module";
import {Router} from '@angular/router';

@Component({
  selector: 'app-login-form-component',
  templateUrl: './login-form-component.component.html',
  styleUrls: ['./login-form-component.component.css']
})
export class LoginFormComponentComponent {
  username: string | undefined;
  password: string | undefined;
  error: boolean | undefined;

  constructor(
    private http: HttpClient,
    private userService: UserServiceService,
    private router: Router
  ) {
    if (userService.getCurrentUser()) {
      this.router.navigate(["/dash"]);
    }
  }

  onSubmit() {
    const formData = {
      username: this.username,
      password: this.password,
    };
    this.http.get(AppModule.API_ADDRESS + 'login', {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa(formData.username + ":" + formData.password)
      })//,
      // observe: "response"
    })
      .subscribe({
        next: response => {
          this.userService.setCurrentUser(response as User, formData.password);
          this.router.navigate(["/dash"]);
        },
        error: (err) => {
          this.error = err.status != 200;
        }
      });
  }
}
