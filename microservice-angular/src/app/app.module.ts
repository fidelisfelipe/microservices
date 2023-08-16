import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ComponentConversionComponent } from './component-conversion/component-conversion.component';
import { ComponentConversionAlertComponent } from './component-conversion-alert/component-conversion-alert.component';
import { AppRoutingModule } from './component-router/app-routing.module';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    ComponentConversionComponent,
    ComponentConversionAlertComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
