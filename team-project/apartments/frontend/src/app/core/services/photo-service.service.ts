import {Injectable} from '@angular/core';
import {User, UserServiceService} from "./user-service.service";
import {AppModule} from "../../app.module";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Accommodation, AccommodationServiceService} from "./accommodation-service.service";
import {forkJoin, map, Observable} from "rxjs";

export interface Photo {
  id: number;
  name: string;
  dateAdded: string;
  author: User;
  file: File | null;
}

@Injectable({
  providedIn: 'root'
})

export class PhotoServiceService {
  currentUser: User

  constructor(
    private http: HttpClient,
    private userService: UserServiceService,
    // private accommodationService: AccommodationServiceService
  ) {
    this.currentUser = userService.getCurrentUser()!;
  }

  public uploadPhoto(file: File | null): Observable<Photo> {
    if (!file){
      return new Observable<Photo>();
    }
    const formData = new FormData();
    formData.append('photo', file, file.name);
    return this.http.post<Photo>(AppModule.API_ADDRESS + "photos", formData, {
      headers: new HttpHeaders({
        'Authorization': 'Basic ' + btoa(this.currentUser.username + ":" + this.currentUser.notHashedPassword)
      })
    })
      .pipe(
        map((response: Photo) => {
          return response;
        })
      )
  }

  public uploadUserPhoto(user: User, photo: File): Observable<User> {
    const formData = new FormData();
    formData.append('photo', photo, photo.name);
    const photoUpdated = this.http.post<Photo>(AppModule.API_ADDRESS + "photos", formData, {
      headers: new HttpHeaders({
        'Authorization': 'Basic ' + btoa(this.currentUser.username + ":" + this.currentUser.notHashedPassword)
      })
    })
      .pipe(
        map((response: Photo) => {
          return response;
        })
      )
    return photoUpdated.pipe(
      map((result: Photo) => {
        user.photo = result;
        this.userService.updateUser(user, user);
        return user;
      })
    )
  }

  public getPhoto(name: string): Observable<Photo> | null{
    if(name == null){
      return null;
    }
    // const photos: Observable<File>[] = accommodation.photos.map(photo => {
      return this.http.get<Photo>(
        AppModule.API_ADDRESS + "photos/" + name
      );
    // });
    // return forkJoin(photos);
  }

  public getAccommodationPhotos(accommodation: Accommodation): Observable<File[]> | null{
    if(accommodation.photos == null){
      return null;
    }
    const photos: Observable<File>[] = accommodation.photos.map(photo => {
      return this.http.get<File>(
        AppModule.API_ADDRESS + "photos/" + photo.name
      )
    });
    return forkJoin(photos);
  }

  public uploadAccommodationPhotos(accommodation: Accommodation, photos: File[]): Observable<Accommodation> {
    const photosUpdated: Observable<Photo>[] = photos.map(photo => {
      return this.http.post<Photo>(AppModule.API_ADDRESS + "photos", photo, {
        headers: new HttpHeaders({
          'Content-Type': 'multipart/form-data',
          'Authorization': 'Basic ' + btoa(this.currentUser.username + ":" + this.currentUser.notHashedPassword)
        })
      })
        .pipe(
          map((response: Photo) => {
            return response;
          })
        )
    });
    return forkJoin(photosUpdated).pipe(
      map((results: Photo[]) => {
        accommodation.photos = results;
        return accommodation;
      })
    );
  }

  public deletePhoto(id: number) {
    const photo = {
      id: id
    }
    console.log(photo);
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


}
