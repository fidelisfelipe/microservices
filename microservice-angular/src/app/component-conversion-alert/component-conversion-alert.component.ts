import {Component, EventEmitter, Input, Output} from '@angular/core';
import {TypeConversion} from "../types/TypeConversion";

@Component({
  selector: 'app-component-conversion-alert',
  templateUrl: './component-conversion-alert.component.html',
  styleUrls: ['./component-conversion-alert.component.css']
})
export class ComponentConversionAlertComponent {
  @Input() typeConversion: TypeConversion | undefined;
  @Output() notify = new EventEmitter();


}
