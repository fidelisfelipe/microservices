import {Component, Input, OnInit} from '@angular/core';
import {TypeConversion} from "../types/type-conversion";
import {ActivatedRoute} from "@angular/router";
import {ConversionService} from "../services/conversion.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-component-conversion-detail',
  templateUrl: './conversion-detail.component.html',
  styleUrls: ['./conversion-detail.component.css']
})
export class ConversionDetailComponent implements OnInit{
  @Input() typeConversion?: TypeConversion | undefined;
  constructor(private route: ActivatedRoute,
              private conversionService: ConversionService,
              private location: Location){}
  ngOnInit(): void {
    this.getConversion();
  }
  onRemove(): void {
    console.log('clean...')
    this.typeConversion = undefined;
  }
  getConversion(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if(id)
    this.conversionService.getConversion(id)
      .subscribe(typeConversion => this.typeConversion = typeConversion);
  }

  goBack() {
    this.location.back();
  }

  save() {
    if(this.typeConversion){
      this.conversionService.updateConversion(this.typeConversion)
        .subscribe(() => this.goBack());
    }
  }

  onSubmit() {
    this.save();
  }
}
