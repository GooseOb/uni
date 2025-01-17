import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginFormComponentComponent} from "./login-form-component/login-form-component.component";
// pszekerowanie ze stron pszy logowaniu i wylogowaniu
const routes: Routes = [
  {path:"login", component: LoginFormComponentComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule {
}
