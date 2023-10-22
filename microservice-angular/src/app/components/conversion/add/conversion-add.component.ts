import { Component } from '@angular/core';
import {TypeConversion} from "../../../types/type-conversion";
import {ConversionService} from "../../../services/conversion.service";
import {Location} from "@angular/common";
import {MessageService} from "../../../services/message.service";

@Component({
  selector: 'app-conversion-add',
  templateUrl: './conversion-add.component.html',
  styleUrls: ['./conversion-add.component.css']
})
export class ConversionAddComponent {

  constructor(private conversionService: ConversionService,
              private messageService: MessageService,
              public location:Location) {
  }

  add(name: string) {
    name = name.trim();
    if (!name) {
      return;
    }
    this.conversionService.addConversion({ name } as TypeConversion)
      .subscribe(typeConversion => {
        this.messageService.add(`Conversion ${name} added`);
      });
  }
}
