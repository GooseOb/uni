import { Component } from '@angular/core';
import {User, UserServiceService} from "../../../core/services/user-service.service";
import {HttpClient} from "@angular/common/http";
import {AppModule} from "../../../app.module";
import {formatDate} from "@angular/common";
import {Router} from "@angular/router"

@Component({
  selector: 'app-registration-form-component',
  templateUrl: './registration-form-component.component.html',
  styleUrls: ['./registration-form-component.component.css']
})
export class RegistrationFormComponentComponent {

  username: string | undefined; //pole dla wpisywanie logina i t.d.
  password: string | undefined; // pole dla wpisywania hasla
  firstName: string | undefined; // imie uzytkownika
  lastName: string | undefined; // nazwisko urzytkownika
  birthDate: string; // data urodzenia
  email: string | undefined; // poczta
  phoneNumber: string | undefined; // numer kontaktowy dla ogloszen
  photo: string | undefined; // photo uzytkownika
  error: string | undefined;

  constructor(
    private http: HttpClient,
    private userService: UserServiceService,
    private router: Router
  ) {
    if(userService.getCurrentUser()){
      router.navigate(["/dash"])
    }
    this.birthDate = "2000-01-01";
  }


  onSubmit() { // wyzwala sie po zatwerdzeniu formularza
    const user = {
      username: this.username,
      password: this.password,
      firstName: this.firstName,
      lastName: this.lastName,
      birthDate: formatDate(this.birthDate, "yyyy-MM-dd", "en-US") + "T00:00:00",
      photo: this.photo,
      phoneNumber: this.phoneNumber,
      email: this.email
    }
    this.http.post(AppModule.API_ADDRESS + "register", user)
      .subscribe({
        next: response => {
          if ((response as User).id) {
            console.log("udało się\n" + JSON.stringify(response));
            this.router.navigate(['/login'])
          }
        },
        error: err => {
          this.error = err;
        }
      })
  }
}
