import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ConversionComponent} from "../component-conversion/conversion.component";

const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full' },
  { path: 'conversion', component: ConversionComponent }
];


@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes),
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
