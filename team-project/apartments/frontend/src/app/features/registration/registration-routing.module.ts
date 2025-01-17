import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegistrationFormComponentComponent} from "./registration-form-component/registration-form-component.component";

const routes: Routes = [
  {path: "register", component: RegistrationFormComponentComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegistrationRoutingModule {
}
