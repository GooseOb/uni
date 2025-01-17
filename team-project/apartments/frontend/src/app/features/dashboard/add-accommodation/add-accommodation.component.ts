import { Component } from '@angular/core';
import {Photo, PhotoServiceService} from "../../../core/services/photo-service.service";
import {Accommodation, AccommodationServiceService} from "../../../core/services/accommodation-service.service";
import {User, UserServiceService} from "../../../core/services/user-service.service";
import {combineLatest} from 'rxjs';

@Component({
  selector: 'app-add-accommodation',
  templateUrl: './add-accommodation.component.html',
  styleUrls: ['./add-accommodation.component.css']
})
export class AddAccommodationComponent {
  name?: string;
  description?: string;
  street?: string;
  city?: string;
  zipCode?: string;
  country?: string;
  photos: File[] | null = null;
  price?: number;
  user: User;
  id?: number;

  constructor(private accommodationService: AccommodationServiceService,
              private userService: UserServiceService,
              public photoService: PhotoServiceService,) {
    this.user = userService.getCurrentUser();
  }

  onSubmit(){
    const fn = (photos: Photo[] | null) => {
      const accommodation = {
        name: this.name,
        description: this.description,
        street: this.street,
        city: this.city,
        zipCode: this.zipCode,
        country: this.country,
        price: this.price,
        photos
      };
      this.accommodationService.addAccommodation(accommodation).subscribe(response => {
        if ((response as Accommodation).id) {
          console.log("udało się\n" + JSON.stringify(response));
          this.id = (response as Accommodation).id;
        }
      })
    }
    if (this.photos) {
      combineLatest(this.photos.map(file => this.photoService.uploadPhoto(file))).subscribe(fn);
    } else {
      fn(null)
    }
  }

  handleFileInput(event: Event) {
    const {files} = event.target as HTMLInputElement;
    this.photos = files && Array.from(files);
  }

}
