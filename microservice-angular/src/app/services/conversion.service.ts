import { Injectable } from '@angular/core';
import {TypeConversion} from "../types/type-conversion";
import {TYPE_CONVERSION_LIST } from "../mock-type-conversion";
import {Observable, of} from "rxjs";
import {MessageService} from "./message.service";

@Injectable({
  providedIn: 'root'
})
export class ConversionService {

  constructor(private messageService:MessageService) { }

  getTypeConversionList(): Observable<TypeConversion[]> {
    const typeConversionList = of(TYPE_CONVERSION_LIST);
    this.messageService.add('ConversionService: fetched type conversion list');
    return typeConversionList;
  }

  getConversion(id: number) {
    const conversion = TYPE_CONVERSION_LIST.find(c => c.id === id)!;
    this.messageService.add(`ConversionService: fetched conversion id=${id}`);
    return of(conversion);
  }
}
