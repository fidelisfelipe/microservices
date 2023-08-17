import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ConversionComponent } from './component-conversion/conversion.component';
import { ConversionAlertComponent } from './component-conversion-alert/conversion-alert.component';
import { AppRoutingModule } from './component-router/app-routing.module';
import {FormsModule} from "@angular/forms";
import { ConversionDetailComponent } from './conversion-detail/conversion-detail.component';
import { MessagesComponent } from './messages/messages.component';

@NgModule({
  declarations: [
    AppComponent,
    ConversionComponent,
    ConversionAlertComponent,
    ConversionDetailComponent,
    MessagesComponent,
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
