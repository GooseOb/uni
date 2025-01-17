import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomePageComponent} from "./home-page/home-page.component";
import {DashboardPageComponent} from "./dashboard-page/dashboard-page.component";
import {AboutUsComponent} from "./about-us/about-us.component";
import {HelpPageComponent} from "./help-page/help-page.component";
import {NotificationsComponent} from "./notifications/notifications.component";
import {isAuthGuard} from '../auth-guard';

const routes: Routes = [
  //polaczenie zeby dzialala nawigacja
  {path: "", component: HomePageComponent},
  {path:"dash", component: DashboardPageComponent, canActivate: [isAuthGuard]},
  {path:"about", component: AboutUsComponent},
  {path:"help", component: HelpPageComponent},
  {path:"notifications", component: NotificationsComponent, canActivate: [isAuthGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContainersRoutingModule { }
