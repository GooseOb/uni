import {LOCALE_ID, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {DashboardRoutingModule} from "./features/dashboard/dashboard-routing.module";
import {LoginRoutingModule} from "./features/login/login-routing.module";
import {RegistrationRoutingModule} from "./features/registration/registration-routing.module";
import {CoreRoutingModule} from "./core/core-routing.module";
import {LoginModule} from "./features/login/login.module";
import {AppRoutingModule} from "./app-routing.module";
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {RegistrationModule} from "./features/registration/registration.module";
import {ContainersRoutingModule} from "./containers/containers-routing.module";
import {DashboardModule} from "./features/dashboard/dashboard.module";
import {ContainersModule} from "./containers/containers.module";
import {AccommodationsRoutingModule} from "./features/accommodations/accommodations-routing.module";

import "@angular/common/locales/global/pl";
import {BookingEditComponent} from './features/bookings/booking-edit/booking-edit.component'
import {BookingsRoutingModule} from "./features/bookings/bookings-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

import {environment} from '../environments/environment';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    BookingEditComponent,
  ],
  imports: [
    BrowserModule,
    LoginModule,
    RegistrationModule,
    DashboardModule,
    DashboardRoutingModule,
    LoginRoutingModule,
    RegistrationRoutingModule,
    CoreRoutingModule,
    ContainersRoutingModule,
    AppRoutingModule,
    ContainersModule,
    AccommodationsRoutingModule,
    BookingsRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [{provide: LOCALE_ID, useValue: 'pl'}],
  exports: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  public static API_ADDRESS= environment.API_ADDRESS
}
