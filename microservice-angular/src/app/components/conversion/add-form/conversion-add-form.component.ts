import {Component, OnInit} from '@angular/core';
import {TypeConversion} from "../../../types/type-conversion";
import {ConversionService} from "../../../services/conversion.service";
import {Location} from "@angular/common";
import {MessageService} from "../../../services/message.service";

@Component({
  selector: 'app-component-conversion-add-form',
  templateUrl: './conversion-add-form.component.html',
  styleUrls: ['./conversion-add-form.component.css']
})
export class ConversionAddFormComponent implements OnInit{

  typeConversionList: TypeConversion[] = [];

  submitted = false;
  model: TypeConversion = {} as TypeConversion;

  constructor(private conversionService: ConversionService,
              private messageService: MessageService) {

  }

  onSubmit() {
    this.add(this.model.name);
    this.submitted = true;
  }

  getConversionList(): void {
    this.conversionService.getTypeConversionList()
      .subscribe(typeConversionList => this.typeConversionList = typeConversionList);
  }

  ngOnInit(): void {
    this.getConversionList();
  }

  add(name: string) {
    name = name.trim();
    if (!name) {
      return;
    }
    this.conversionService.addConversion({ name } as TypeConversion)
      .subscribe(typeConversion => {
        this.typeConversionList.push(typeConversion);
        window.alert("Conversion added successfully!");

      });
  }
  delete(type: TypeConversion) {

    this.conversionService.deleteConversion(type).subscribe(
      () => {
        this.typeConversionList = this.typeConversionList.filter((h) => h !== type);
      }
    );
  }
}
