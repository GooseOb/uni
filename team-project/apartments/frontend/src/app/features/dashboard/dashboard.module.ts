import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';

import {DashboardRoutingModule} from './dashboard-routing.module';
import {DashboardUserViewComponent} from './dashboard-user-view/dashboard-user-view.component';
import {FormsModule} from "@angular/forms";
import {AddAccommodationComponent} from './add-accommodation/add-accommodation.component';
import {ListHostAccommodationsComponent} from './list-host-accommodations/list-host-accommodations.component';
import {ListBookingsComponent} from './list-bookings/list-bookings.component';


@NgModule({
  declarations: [
    DashboardUserViewComponent,
    AddAccommodationComponent,
    ListHostAccommodationsComponent,
    ListBookingsComponent
  ],
  exports: [
    DashboardUserViewComponent,
    AddAccommodationComponent,
    ListHostAccommodationsComponent,
    ListBookingsComponent
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    FormsModule,
    NgOptimizedImage
  ]
})
export class DashboardModule {
}
