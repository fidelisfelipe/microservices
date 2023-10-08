import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ConversionComponent } from './component-conversion/conversion.component';
import { ConversionAlertComponent } from './component-conversion-alert/conversion-alert.component';
import { AppRoutingModule } from './component-router/app-routing.module';
import { FormsModule } from "@angular/forms";
import { ConversionEditComponent } from './component-conversion-edit/conversion-edit.component';
import { MessagesComponent } from './messages/messages.component';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientInMemoryWebApiModule } from "angular-in-memory-web-api";
import { InMemoryDataService } from "./data-memory/in-memory-data.service";
import { ConversionSearchComponent } from './component-conversion-search/conversion-search.component';
import { ConversionAddFormComponent } from './component-conversion-add-form/conversion-add-form.component';
import { ConversionAddComponent } from './component-conversion-add/conversion-add.component';
import { ConversionListComponent } from './component-conversion-list/conversion-list.component';
import { ConversionService } from "./services/conversion.service";

import { LogsReaderComponent } from './logs-reader/logs-reader.component';
import {RxStompService} from "./services/rx-stomp.service";
import {rxStompServiceFactory} from "./factores/rx-stomp-service-factory";

@NgModule({
  declarations: [
    AppComponent,
    ConversionComponent,
    ConversionAlertComponent,
    ConversionEditComponent,
    MessagesComponent,
    ConversionSearchComponent,
    ConversionAddFormComponent,
    ConversionAddComponent,
    ConversionListComponent,
    LogsReaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    //HttpClientInMemoryWebApiModule.forRoot(
    //  InMemoryDataService, { dataEncapsulation: false }
    //)
  ],
  providers: [
    ConversionService,
    { provide: RxStompService,
      useFactory: rxStompServiceFactory, }],
  bootstrap: [AppComponent]
})
export class AppModule { }
