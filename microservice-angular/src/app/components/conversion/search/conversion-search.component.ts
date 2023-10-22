import {Component, OnInit} from '@angular/core';
import {Observable, Subject} from "rxjs";
import {TypeConversion} from "../../../types/type-conversion";
import {ConversionService} from "../../../services/conversion.service";
import {
  debounceTime, distinctUntilChanged, switchMap, tap
} from 'rxjs/operators';
import {Location} from "@angular/common";

@Component({
  selector: 'app-component-conversion-search',
  templateUrl: './conversion-search.component.html',
  styleUrls: ['./conversion-search.component.css']
})
export class ConversionSearchComponent implements OnInit {
  typeConversionList$!: Observable<TypeConversion[]>;
  private searchTerms = new Subject<string>();

  constructor(private conversionService: ConversionService,
              public location: Location) {

  }

  ngOnInit(): void {
    this.typeConversionList$ = this.searchTerms.pipe(
       debounceTime(300),
       distinctUntilChanged(),
       switchMap((term: string) => this.conversionService.searchTypeConversion(term)),
       tap(results => console.log('Resultados retornados:', results))
    );
  }

  search(term: string) {
    this.searchTerms.next(term);
  }


}
