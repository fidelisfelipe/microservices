import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import {Observable} from "rxjs";
@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService{

  constructor() { }

  createDb(){
    const typeConversionList = [
      { id: 1, name: 'EUR', value: 1 },
      { id: 2, name: 'USD', value: 1.19 },
      { id: 3, name: 'GBP', value: 0.86 },
      { id: 4, name: 'JPY', value: 130.91 },
      { id: 5, name: 'CHF', value: 1.09 },
      { id: 6, name: 'CAD', value: 1.47 },
      { id: 7, name: 'AUD', value: 1.55 },
      { id: 8, name: 'CNY', value: 7.71 },
      { id: 9, name: 'HKD', value: 9.23 },
      { id: 10, name: 'NZD', value: 1.67 }];
    return {typeConversionList};
  }
  genId(typeConversionList: { id: number; }[]): number {
    return typeConversionList.length > 0 ? Math.max(...typeConversionList.map(typeConversion => typeConversion.id)) + 1 : 11;
  }
}
