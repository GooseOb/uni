import { Component } from '@angular/core';
import {Accommodation, AccommodationServiceService} from "../../../core/services/accommodation-service.service";
import {HttpClient} from "@angular/common/http";
import {PhotoServiceService} from "../../../core/services/photo-service.service";
import {ActivatedRoute} from "@angular/router";
import {AppModule} from "../../../app.module";
import {map} from "rxjs";

@Component({
  selector: 'app-accommodation-edit',
  templateUrl: './accommodation-edit.component.html',
  styleUrls: ['./accommodation-edit.component.css']
})
export class AccommodationEditComponent {
  accommodation: Accommodation | undefined;
  photo: File | null = null;
  change: string | undefined;

  constructor(
    private http:HttpClient,
    public accommodationService: AccommodationServiceService,
    public photoService: PhotoServiceService,
    private route: ActivatedRoute
  ) {
    this.route.queryParams.subscribe(params => {
      if(params["a"]){
        this.accommodationService.getAccommodation(params["a"]).subscribe(accommodation => {
          this.accommodation = accommodation;
          console.log(this.accommodation);
        });
      }
    })
  }

  onSubmit(){
    if(this.change == "photo"){
      this.photoService.uploadPhoto(this.photo).subscribe(photo => {
        this.accommodation?.photos.push(photo);
        this.accommodationService.updateAccommodation(this.accommodation);
      })
    } else {
      this.accommodationService.updateAccommodation(this.accommodation);
      this.change = "";
    }
  }

  handleFileInput(event: Event) {
    const target = event.target as HTMLInputElement;
    const files: FileList | null = target.files;
    if (files) {
      this.photo = files.item(0);
    } else {
      this.photo = null;
    }
  }


  protected readonly AppModule = AppModule;
  protected readonly console = console;
  protected readonly map = map;
  protected readonly File = File;
}
