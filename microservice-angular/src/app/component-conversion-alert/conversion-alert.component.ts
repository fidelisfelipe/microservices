import {Component, EventEmitter, Input, Output} from '@angular/core';
import {TypeConversion} from "../types/type-conversion";

@Component({
  selector: 'app-component-conversion-alert',
  templateUrl: './conversion-alert.component.html',
  styleUrls: ['./conversion-alert.component.css']
})
export class ConversionAlertComponent {
  @Input() typeConversion: TypeConversion | undefined;
  @Output() notify = new EventEmitter();


}
