import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AccommodationShowComponent} from "./accommodation-show/accommodation-show.component";
import {AccommodationEditComponent} from "./accommodation-edit/accommodation-edit.component";

const routes: Routes = [
  {path: "show", component: AccommodationShowComponent},
  {path: "edit", component: AccommodationEditComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccommodationsRoutingModule { }
