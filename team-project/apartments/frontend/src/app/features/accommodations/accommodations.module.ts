import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccommodationsRoutingModule } from './accommodations-routing.module';
import {
  AccommodationsListComponentComponent
} from "./accommodations-list-component/accommodations-list-component.component";
import { AccommodationShowComponent } from './accommodation-show/accommodation-show.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AccommodationFilterComponent } from './accommodation-filter/accommodation-filter.component';
import { AccommodationEditComponent } from './accommodation-edit/accommodation-edit.component';


@NgModule({
  declarations: [
    AccommodationsListComponentComponent,
    AccommodationShowComponent,
    AccommodationFilterComponent,
    AccommodationEditComponent
  ],
    exports: [
        AccommodationsListComponentComponent,
        AccommodationFilterComponent
    ],
  imports: [
    CommonModule,
    AccommodationsRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AccommodationsModule { }
