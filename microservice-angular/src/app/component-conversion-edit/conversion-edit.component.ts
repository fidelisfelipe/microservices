import {Component, Input, OnInit} from '@angular/core';
import {TypeConversion} from "../types/type-conversion";
import {ActivatedRoute} from "@angular/router";
import {ConversionService} from "../services/conversion.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-component-conversion-edit',
  templateUrl: './conversion-edit.component.html',
  styleUrls: ['./conversion-edit.component.css']
})
export class ConversionEditComponent implements OnInit{
  @Input() typeConversion?: TypeConversion | undefined;
  constructor(private route: ActivatedRoute,
              private conversionService: ConversionService,
              public location: Location){}
  ngOnInit(): void {
    this.getConversion();
  }
  onRemove(): void {
    console.log('clean...')
    this.typeConversion = {} as TypeConversion
  }
  getConversion(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if(id)
    this.conversionService.getConversion(id)
      .subscribe(typeConversion => this.typeConversion = typeConversion);
  }

  save() {
    if(this.typeConversion){
      this.conversionService.updateConversion(this.typeConversion)
        .subscribe(() => this.location.back());
    }
  }

  onSubmit() {
    this.save();
  }

  onNotify() {
    window.alert('You will be notified when the type conversion on selected');
  }
}
