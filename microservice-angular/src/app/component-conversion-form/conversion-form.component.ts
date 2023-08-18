import {Component, OnInit} from '@angular/core';
import {TypeConversion} from "../types/type-conversion";
import {ConversionService} from "../services/conversion.service";

@Component({
  selector: 'app-component-conversion-form',
  templateUrl: './conversion-form.component.html',
  styleUrls: ['./conversion-form.component.css']
})
export class ConversionFormComponent implements OnInit{

  typeConversionList: TypeConversion[] = [];

  submitted = false;
  model: TypeConversion = {id: 0, name: ''};

  constructor(private conversionService: ConversionService){

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
