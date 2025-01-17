import {Injectable} from '@angular/core';
import {Photo, PhotoServiceService} from "./photo-service.service";
import {User, UserServiceService} from "./user-service.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map, Observable, Subscription} from "rxjs";
import {AppModule} from "../../app.module";
import {Booking, BookingsService} from "./bookings.service";

export interface Accommodation {
  accommodation: Observable<Photo[]>;
  id: number;
  dateAdded: string;
  name: string;
  description: string;
  street: string;
  city: string;
  zipCode: string;
  country: string;
  photos: Photo[];
  host: User;
  price: number;
}

@Injectable({
  providedIn: 'root'
})

export class AccommodationServiceService {
  currentUser: User;

  constructor(private http: HttpClient,
              private userService: UserServiceService,
              private photoService: PhotoServiceService,
              private bookingsService: BookingsService) {
    this.currentUser = userService.getCurrentUser();
  }

  public getAllAccommodations(): Observable<Accommodation[]> {
    let accommodations: Accommodation[] | null;
    accommodations = null;
    return this.http.get<Accommodation[]>(
      AppModule.API_ADDRESS + "accommodations"
    )
      .pipe(
        map((response: Accommodation[]) => {
          return response;
        })
      );
  }

  public getAccommodation(accommodationId: number): Observable<Accommodation> {
    return this.http.get<Accommodation>(
      AppModule.API_ADDRESS + "accommodations/" + accommodationId
    )
      .pipe(
        map((response: Accommodation) => {
          return response;
        })
      );
  }


  public getHostAccommodations(): Observable<Accommodation[]> {
    return this.http.get<Accommodation[]>(
      AppModule.API_ADDRESS + "accommodations/hosts/" + this.currentUser.id,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Basic ' + btoa(this.currentUser.username + ":" + this.currentUser.notHashedPassword)
        })
      })
      .pipe(
        map((response: Accommodation[]) => {
          return response;
        })
      );
  }

  public addAccommodation(accommodation: any) {
    // const photos: Observable<Photo>[] = accommodation.photos.map((photo: Photo) => {
    //   photos.push(this.photoService.uploadPhoto(photo.file));
    // })
    // accommodation.photos = forkJoin(photos);
    return this.http.post(AppModule.API_ADDRESS + "accommodations", accommodation, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa(this.currentUser.username + ":" + this.currentUser.notHashedPassword)
      })
    })
  }

  public updateAccommodation(accommodation: any) {
    this.http.put(AppModule.API_ADDRESS + "accommodations", accommodation, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa(this.currentUser.username + ":" + this.currentUser.notHashedPassword)
      })
    }).subscribe(response => {
      if ((response as Accommodation).id) {
        console.log("udało się\n" + JSON.stringify(response));
      }
    })
  }

  public deleteAccommodation(accommodation: Accommodation) {
    return this.http.delete(AppModule.API_ADDRESS + "accommodations", {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa(this.currentUser.username + ":" + this.currentUser.notHashedPassword)
      }),
      body: accommodation
    })
  }

  public deleteAccommodationPhoto(accommodation: Accommodation, photo: Photo) {
    accommodation.photos = accommodation.photos.filter(p => {
      return p != photo
    })
    this.updateAccommodation(accommodation);
    console.log(accommodation);
    return this.http.delete<Photo>(AppModule.API_ADDRESS + "photos", {
      headers: new HttpHeaders({
        'Authorization': 'Basic ' + btoa(this.currentUser.username + ":" + this.currentUser.notHashedPassword)
      }),
      body: photo
    })
      .pipe(
        map((response: Photo) => {
          return response;
        })
      )
  }

  public getAccommodationsBookings(accommodation: Accommodation): Observable<Booking[]>{
    return this.http.get<Booking[]>(
      AppModule.API_ADDRESS + "bookings/accommodations/" + accommodation.id,
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

  public getPresentAccommodationsBookings(accommodation: Accommodation): Observable<Booking[]>{
    return this.http.get<Booking[]>(
      AppModule.API_ADDRESS + "bookings/accommodations/present/" + accommodation.id,
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
}
