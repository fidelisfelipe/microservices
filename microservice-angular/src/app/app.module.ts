import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ConversionComponent } from './components/conversion/home/conversion.component';
import { ConversionAlertComponent } from './components/conversion/alert/conversion-alert.component';
import { AppRoutingModule } from './component-router/app-routing.module';
import { FormsModule } from "@angular/forms";
import { ConversionEditComponent } from './components/conversion/edit/conversion-edit.component';
import { MessagesComponent } from './messages/messages.component';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientInMemoryWebApiModule } from "angular-in-memory-web-api";
import { InMemoryDataService } from "./data-memory/in-memory-data.service";
import { ConversionSearchComponent } from './components/conversion/search/conversion-search.component';
import { ConversionAddFormComponent } from './components/conversion/add-form/conversion-add-form.component';
import { ConversionAddComponent } from './components/conversion/add/conversion-add.component';
import { ConversionListComponent } from './components/conversion/list/conversion-list.component';
import { ConversionService } from "./services/conversion.service";

import { LogsReaderComponent } from './logs-reader/logs-reader.component';
import {RxStompService} from "./services/rx-stomp.service";
import {rxStompServiceFactory} from "./factores/rx-stomp-service-factory";
import {ConversionModule} from "./components/conversion/conversion.module";

@NgModule({
  declarations: [
    AppComponent,
    MessagesComponent,
    LogsReaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ConversionModule,
    //HttpClientInMemoryWebApiModule.forRoot(
    //  InMemoryDataService, { dataEncapsulation: false }
    //)
  ],
  providers: [
    { provide: RxStompService,
      useFactory: rxStompServiceFactory, }],
  bootstrap: [AppComponent]
})
export class AppModule { }
