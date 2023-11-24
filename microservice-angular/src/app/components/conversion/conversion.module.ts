import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from "@angular/forms";
import {ConversionComponent} from "./home/conversion.component";
import {ConversionService} from "../../services/conversion.service";
import {ConversionAlertComponent} from "./alert/conversion-alert.component";
import {ConversionEditComponent} from "./edit/conversion-edit.component";
import {ConversionSearchComponent} from "./search/conversion-search.component";
import {ConversionAddFormComponent} from "./add-form/conversion-add-form.component";
import {ConversionAddComponent} from "./add/conversion-add.component";
import {ConversionListComponent} from "./list/conversion-list.component";
import {ConversionRoutingModule} from "./conversion-routing.module";
import { ConversionExchangeComponent } from './exchange/conversion-exchange.component';
import { ConversionMenuComponent } from './menu/conversion-menu.component';


@NgModule({
  declarations: [
    ConversionComponent,
    ConversionAlertComponent,
    ConversionEditComponent,
    ConversionSearchComponent,
    ConversionAddFormComponent,
    ConversionAddComponent,
    ConversionListComponent,
    ConversionExchangeComponent,
    ConversionMenuComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ConversionRoutingModule
  ],
  providers: [
    ConversionService
  ]
})
export class ConversionModule { }
