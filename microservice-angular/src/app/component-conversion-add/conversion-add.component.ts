import { Component } from '@angular/core';
import {TypeConversion} from "../types/type-conversion";
import {ConversionService} from "../services/conversion.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-conversion-add',
  templateUrl: './conversion-add.component.html',
  styleUrls: ['./conversion-add.component.css']
})
export class ConversionAddComponent {

  constructor(private conversionService: ConversionService,
              public location:Location) {
  }

  add(name: string) {
    name = name.trim();
    if (!name) {
      return;
    }
    this.conversionService.addConversion({ name } as TypeConversion)
      .subscribe(typeConversion => {
        console.log()
      });
  }
}
