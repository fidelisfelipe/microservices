import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ConversionComponent} from "../component-conversion/conversion.component";
import {ConversionEditComponent} from "../component-conversion-edit/conversion-edit.component";
import {ConversionAddFormComponent} from "../component-conversion-add-form/conversion-add-form.component";
import {ConversionAddComponent} from "../component-conversion-add/conversion-add.component";
import {ConversionListComponent} from "../component-conversion-list/conversion-list.component";
import {ConversionSearchComponent} from "../component-conversion-search/conversion-search.component";

const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full' },
  { path: 'conversion', component: ConversionComponent },
  { path: 'conversion/:id', component: ConversionEditComponent },
  { path: 'conversion-add', component: ConversionAddComponent },
  { path: 'conversion-add-form', component: ConversionAddFormComponent },
  { path: 'conversion-list', component: ConversionListComponent },
  { path: 'conversion-search', component: ConversionSearchComponent },
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes),
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
