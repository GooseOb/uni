import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegistrationRoutingModule } from './registration-routing.module';
import { RegistrationFormComponentComponent } from './registration-form-component/registration-form-component.component';
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    RegistrationFormComponentComponent
  ],
    imports: [
        CommonModule,
        RegistrationRoutingModule,
        FormsModule
    ]
})
export class RegistrationModule { }
