import {Component,  Input, OnInit} from '@angular/core';
import {TypeConversion, typeConversionList} from "../types/TypeConversion";

@Component({
  selector: 'app-component-conversion',
  templateUrl: './component-conversion.component.html',
  styleUrls: ['./component-conversion.component.css']
})
export class ComponentConversionComponent implements OnInit {

  typeConversionList = [...typeConversionList];
  @Input() typeConversion: TypeConversion | undefined;


  constructor() {
  }

  ngOnInit(): void {
  }

  selectTypeConversion(typeConversion: TypeConversion): void {
    console.log('selected item '+typeConversion.name)
    this.typeConversion = typeConversion;
  }

  clear(): void {
    console.log('clean...')
    this.typeConversion = undefined;
  }

  onNotify() {
    window.alert('You will be notified when the type conversion on selected');
  }


}
