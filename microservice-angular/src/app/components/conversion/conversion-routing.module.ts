import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ConversionComponent} from "./home/conversion.component";
import {ConversionEditComponent} from "./edit/conversion-edit.component";
import {ConversionAddFormComponent} from "./add-form/conversion-add-form.component";
import {ConversionAddComponent} from "./add/conversion-add.component";
import {ConversionListComponent} from "./list/conversion-list.component";
import {ConversionSearchComponent} from "./search/conversion-search.component";
import {ConversionExchangeComponent} from "./exchange/conversion-exchange.component";

const routes: Routes = [
  { path: 'conversion', component: ConversionComponent },
  { path: 'conversion/:id', component: ConversionEditComponent },
  { path: 'conversion-add', component: ConversionAddComponent },
  { path: 'conversion-add-form', component: ConversionAddFormComponent },
  { path: 'conversion-list', component: ConversionListComponent },
  { path: 'conversion-search', component: ConversionSearchComponent },
  { path: 'conversion-exchange', component: ConversionExchangeComponent },
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes),
  ],
  exports: [RouterModule]
})
export class ConversionRoutingModule { }
