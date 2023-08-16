import {Component,  Input, OnInit} from '@angular/core';
import {TypeConversion, typeConversionList} from "../types/TypeConversion";

@Component({
  selector: 'app-component-conversion',
  templateUrl: './conversion.component.html',
  styleUrls: ['./conversion.component.css']
})
export class ConversionComponent implements OnInit {

  typeConversionList = [...typeConversionList];
  @Input() typeConversion: TypeConversion | undefined;


  constructor() {
  }

  ngOnInit(): void {
  }

  onSelect(typeConversion: TypeConversion): void {
    console.log('selected item '+typeConversion.name)
    this.typeConversion = typeConversion;
  }

  onNotify() {
    window.alert('You will be notified when the type conversion on selected');
  }


}
