import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AppModule} from "../../app.module";
import {Photo, PhotoServiceService} from "./photo-service.service";
import {BehaviorSubject, forkJoin, Observable} from "rxjs";

export interface User { // system sprawdzenia loginu i hasla
  id: number;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  photo: Photo;
  birthDate: string;
  notHashedPassword: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  private readonly USER_KEY = 'currentUser';
  private readonly currentUserSubject: BehaviorSubject<User | null>;
  public readonly currentUser$: Observable<User | null>;

  constructor(
    private http: HttpClient,
  ) {
    const userJson = localStorage.getItem(this.USER_KEY);
    this.currentUserSubject = new BehaviorSubject(userJson ? JSON.parse(userJson) : null);
    this.currentUser$ = this.currentUserSubject.asObservable();
  }

  public setCurrentUser(user: User | null, password?: string) {
    if (password && user) {
      user.notHashedPassword = password;
    }
    localStorage.setItem(this.USER_KEY, JSON.stringify(user));
    this.currentUserSubject.next(user);
  }

  public getCurrentUser(): User {
    if (!this.currentUserSubject.value) {
      const userJson = localStorage.getItem(this.USER_KEY);
      const user = userJson ? JSON.parse(userJson) : null;
      if (user) {
        this.currentUserSubject.next(user);
      }
      return user;
    }
    return this.currentUserSubject.value;
  }


  public clearCurrentUser() {
    localStorage.removeItem(this.USER_KEY);
    this.setCurrentUser(null);
  }

  public reloadUser(username: string, password: string) {
    this.http.get(AppModule.API_ADDRESS + "login", {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa(username + ":" + password)
      })
    }).subscribe(response => {
      console.log(JSON.stringify(response));
      this.setCurrentUser(response as User, password);
    });
  }

  public updateUser(userToUpdate: User, userUpdated: User): Observable<User>{
    return this.http.put<User>(
      AppModule.API_ADDRESS + "user",
      userUpdated,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Basic ' + btoa(userToUpdate.username + ":" + userToUpdate.notHashedPassword)
        })
      })
  }
}
