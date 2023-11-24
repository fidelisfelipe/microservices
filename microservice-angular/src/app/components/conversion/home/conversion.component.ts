import {Component, OnInit} from '@angular/core';
import {TypeConversion} from "../../../types/type-conversion";
import {ConversionService} from "../../../services/conversion.service";
import {MessageService} from "../../../services/message.service";

@Component({
  selector: 'app-component-conversion',
  templateUrl: './conversion.component.html',
  styleUrls: ['./conversion.component.css']
})
export class ConversionComponent implements OnInit {

  typeConversionList: TypeConversion[] = [];
  from?: TypeConversion;
  to?: TypeConversion;
  quantity?: String;
  result?: string;

  constructor(private serviceConversion: ConversionService,
              private messageService: MessageService) {
  }


  ngOnInit(): void {
    this.getConversionList();
  }

  getConversionList(): void {
    this.serviceConversion.getTypeConversionList()
      .subscribe(typeConversionList => this.typeConversionList = typeConversionList);
  }

  onPressConversion() {
    this.messageService.add(`from: ${this.from} to ${this.to} quantity ${this.quantity}`);
    this.serviceConversion.conversion(this.from, this.to, this.quantity)
      .subscribe(
        data => {
          this.result = JSON.stringify(data);
          this.messageService.add(`result conversion: ${data}`);
        }
      );
  }
}
