import {Component,  Input, OnInit} from '@angular/core';
import {TypeConversion} from "../types/type-conversion";
import {ConversionService} from "../services/conversion.service";
import {MessageService} from "../services/message.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-component-conversion',
  templateUrl: './conversion.component.html',
  styleUrls: ['./conversion.component.css']
})
export class ConversionComponent implements OnInit {


  constructor(public location: Location){

  }

  ngOnInit(): void {

  }

}
