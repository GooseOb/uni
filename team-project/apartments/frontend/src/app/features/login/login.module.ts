import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginFormComponentComponent } from './login-form-component/login-form-component.component';
import {FormsModule} from "@angular/forms";
import {HttpClient, HttpClientModule} from "@angular/common/http";


@NgModule({
  declarations: [
    LoginFormComponentComponent
  ],
  exports: [
    LoginFormComponentComponent
  ],
  imports: [
    CommonModule,
    LoginRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [HttpClient]
})
export class LoginModule { }
