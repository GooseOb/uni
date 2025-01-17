import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ContainersRoutingModule } from './containers-routing.module';
import { HomePageComponent } from './home-page/home-page.component';
import {AccommodationsModule} from "../features/accommodations/accommodations.module";
import { DashboardPageComponent } from './dashboard-page/dashboard-page.component';
import {DashboardModule} from "../features/dashboard/dashboard.module";
import { AboutUsComponent } from './about-us/about-us.component';
import { HelpPageComponent } from './help-page/help-page.component';
import { NotificationsComponent } from './notifications/notifications.component';
import {NotificationsModule} from "../features/notifications/notifications.module";


@NgModule({
  declarations: [
    HomePageComponent,
    DashboardPageComponent,
    AboutUsComponent,
    HelpPageComponent,
    NotificationsComponent,
  ],
    imports: [
        CommonModule,
        ContainersRoutingModule,
        AccommodationsModule,
        DashboardModule,
        NotificationsModule
    ]
})
export class ContainersModule { }
