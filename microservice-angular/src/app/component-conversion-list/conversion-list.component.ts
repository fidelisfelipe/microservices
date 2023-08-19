import {Component, Input, OnInit} from '@angular/core';
import {TypeConversion} from "../types/type-conversion";
import {ConversionService} from "../services/conversion.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-conversion-list',
  templateUrl: './conversion-list.component.html',
  styleUrls: ['./conversion-list.component.css']
})
export class ConversionListComponent implements OnInit {

  typeConversionList:TypeConversion[] = [];
  constructor(private conversionService: ConversionService){
  }
  ngOnInit(): void {
    this.getConversionList();
  }
  delete(type: TypeConversion) {
    this.typeConversionList = this.typeConversionList.filter((h) => h !== type);
    this.conversionService.deleteConversion(type).subscribe();
  }
  getConversionList(): void {
    this.conversionService.getTypeConversionList()
      .subscribe(typeConversionList => this.typeConversionList = typeConversionList);
  }
}
