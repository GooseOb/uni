import {Component, LOCALE_ID, OnInit} from '@angular/core';
import {Accommodation, AccommodationServiceService} from "../../../core/services/accommodation-service.service";
import {formatNumber} from "@angular/common";
import {AppModule} from "../../../app.module";

@Component({
  selector: 'app-accommodations-list-component',
  templateUrl: './accommodations-list-component.component.html',
  styleUrls: ['./accommodations-list-component.component.css']
})
export class AccommodationsListComponentComponent implements OnInit {
  accommodations: Accommodation[];

  constructor(private accommodationService: AccommodationServiceService) {
    this.accommodations = [];
  }

  ngOnInit(): void {
    this.accommodationService.getAllAccommodations().subscribe((data: Accommodation[]) => {
      this.accommodations = data;
    });
  }

  protected readonly formatNumber = formatNumber;
  protected readonly LOCALE_ID = LOCALE_ID;
  protected readonly AppModule = AppModule;
}
