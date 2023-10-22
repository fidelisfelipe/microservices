import {Component, OnInit} from '@angular/core';
import {ConversionService} from "../../../services/conversion.service";

@Component({
  selector: 'app-exchange',
  templateUrl: './conversion-exchange.component.html',
  styleUrls: ['./conversion-exchange.component.css']
})
export class ConversionExchangeComponent implements OnInit{
  exchangeList: any;

  constructor(private conversionService: ConversionService){
  }

  ngOnInit(): void {
    this.getExchangeList();
  }

  private getExchangeList() {
    this.conversionService.getExchangeList().subscribe(data => {
      this.exchangeList = data;
    });
  }
}
