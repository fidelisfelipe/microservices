import { Component } from '@angular/core';
import {ConversionService} from "./services/conversion.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'microservice-angular';
  constructor(public location: Location) {
  }
}
