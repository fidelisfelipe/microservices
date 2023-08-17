import {Component,  Input, OnInit} from '@angular/core';
import {TypeConversion} from "../types/type-conversion";
import {ConversionService} from "../services/conversion.service";
import {Observable} from "rxjs";
import {TYPE_CONVERSION_LIST} from "../mock-type-conversion";
import {MessageService} from "../services/message.service";

@Component({
  selector: 'app-component-conversion',
  templateUrl: './conversion.component.html',
  styleUrls: ['./conversion.component.css']
})
export class ConversionComponent implements OnInit {

  typeConversionList:TypeConversion[] = [];
  @Input() typeConversion?: TypeConversion;

  constructor(private conversionService: ConversionService, private messageService:MessageService) {
  }

  ngOnInit(): void {
    this.getConversionList();
  }

  onNotify() {
    window.alert('You will be notified when the type conversion on selected');
  }

 getConversionList(): void {
    this.conversionService.getTypeConversionList()
      .subscribe(typeConversionList => this.typeConversionList = typeConversionList);
  }

  delete(type: TypeConversion) {
    this.typeConversionList = this.typeConversionList.filter((h) => h !== type);
    this.conversionService.deleteConversion(type).subscribe();
  }

  search($event: any) {

  }
  add(name: string) {
    name = name.trim();
    if (!name) {
      return;
    }
    this.conversionService.addConversion({ name } as TypeConversion)
      .subscribe(typeConversion => {
        this.typeConversionList.push(typeConversion);
      });
  }
}
