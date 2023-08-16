import {Component, Input} from '@angular/core';
import {TypeConversion} from "../types/TypeConversion";

@Component({
  selector: 'app-conversion-detail',
  templateUrl: './conversion-detail.component.html',
  styleUrls: ['./conversion-detail.component.css']
})
export class ConversionDetailComponent {
  @Input() typeConversion?: TypeConversion | undefined;
  onRemove(): void {
    console.log('clean...')
    this.typeConversion = undefined;
  }
}
