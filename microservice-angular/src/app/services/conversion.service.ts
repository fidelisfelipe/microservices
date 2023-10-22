import { Injectable } from '@angular/core';
import {TypeConversion} from "../types/type-conversion";
import {TYPE_CONVERSION_LIST } from "../mock-type-conversion";
import {Observable, of} from "rxjs";
import {MessageService} from "./message.service";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import {Conversion} from "../model/Conversion";
import {Exchange} from "../types/exchange.type";

@Injectable({
  providedIn: 'root'
})
export class ConversionService {

  private host = 'http://localhost:8765';

  private pathType = this.host+'/currency-conversion-service/currency-conversion-feign/type';

  private pathConversion = this.host+'/currency-conversion-service/currency-conversion-feign/from';

  private pathExchange = this.host+'/currency-conversion-service/currency-conversion-feign/exchange/list';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService:MessageService) { }

  getTypeConversionList(): Observable<TypeConversion[]> {
    return this.http.get<TypeConversion[]>(this.pathType+"/list")
      .pipe(
        tap(_ => this.log('fetched type conversion list')),
        catchError(this.handleError<TypeConversion[]>('getTypeConversionList', []))
      );
  }

  getConversion(id: number) {
    const url = `${this.pathType}/${id}`;

    return this.http.get<TypeConversion>(url).pipe(
      tap(_ => this.log(`fetched conversion id=${id}`)),
      catchError(this.handleError<TypeConversion>(`getConversion id=${id}`))
    );
  }
  private log(message: string) {
    this.messageService.add(`ConversionService: ${message}`);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> =>{
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    }
  }

  updateConversion(typeConversion: TypeConversion) {
    return this.http.put(this.pathType, typeConversion, this.httpOptions).pipe(
      tap(_ => this.log(`updated conversion id=${typeConversion.id}`)),
      catchError(this.handleError<any>('updateConversion'))
    );
  }

  addConversion(typeConversion: TypeConversion): Observable<TypeConversion>{
    return this.http.post(this.pathType, typeConversion, this.httpOptions).pipe(
      tap((newTypeConversion: any) => this.log(`added conversion w/ id=${newTypeConversion.id}`)),
      catchError(this.handleError<TypeConversion>('addConversion'))
    );
  }

  deleteConversion(type: TypeConversion) {
    const httpOptions = {
      headers: new HttpHeaders()
    };
    const url = `${this.pathType}/${type.id}`;

    return this.http.delete<TypeConversion>(url, httpOptions).pipe(
      tap(_ => this.log(`deleted conversion id=${type.id}`)),
      catchError(this.handleError<TypeConversion>('deleteConversion'))
    );
  }

  searchTypeConversion(name: string): Observable<TypeConversion[]> {
    if (!name.trim()) {
      return of([]);
    }
    return this.http.get<TypeConversion[]>(this.pathType+'/name/'+name).pipe(
      tap(_ => this.log(`found conversions matching "${name}"`)),
      catchError(this.handleError<TypeConversion[]>('searchConversion', []))
    );
  }

  conversion(from: TypeConversion | undefined, to: TypeConversion | undefined, quantity: String | undefined): Observable<Conversion> {
    const url = `${this.pathConversion}/${from}/to/${to}/quantity/${quantity}`;

    return this.http.get<Conversion>(url).pipe(
      tap(_ => this.log(`fetched conversion from/${from}/to/${to}/quantity/${quantity}`)),
      catchError(this.handleError<Conversion>(`getConversion from/${from}/to/${to}/quantity/${quantity}`))
    );
  }

  getExchangeList() {
    const url = `${this.pathExchange}`;
    return this.http.get<Exchange[]>(url).pipe(
      tap(_ => this.log(`fetched exchange list`)),
      catchError(this.handleError<Exchange[]>(`getExchangeList`))
    );
  }
}
