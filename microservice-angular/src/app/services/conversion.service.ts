import { Injectable } from '@angular/core';
import {TypeConversion} from "../types/type-conversion";
import {TYPE_CONVERSION_LIST } from "../mock-type-conversion";
import {Observable, of} from "rxjs";
import {MessageService} from "./message.service";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ConversionService {

  private typeConversionUrl = 'http://localhost:8765/currency-conversion-service/currency-conversion-feign/type';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService:MessageService) { }

  getTypeConversionList(): Observable<TypeConversion[]> {
    return this.http.get<TypeConversion[]>(this.typeConversionUrl+"/list")
      .pipe(
        tap(_ => this.log('fetched type conversion list')),
        catchError(this.handleError<TypeConversion[]>('getTypeConversionList', []))
      );
  }

  getConversion(id: number) {
    const url = `${this.typeConversionUrl}/${id}`;

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
    return this.http.put(this.typeConversionUrl, typeConversion, this.httpOptions).pipe(
      tap(_ => this.log(`updated conversion id=${typeConversion.id}`)),
      catchError(this.handleError<any>('updateConversion'))
    );
  }

  addConversion(typeConversion: TypeConversion): Observable<TypeConversion>{
    return this.http.post(this.typeConversionUrl, typeConversion, this.httpOptions).pipe(
      tap((newTypeConversion: any) => this.log(`added conversion w/ id=${newTypeConversion.id}`)),
      catchError(this.handleError<TypeConversion>('addConversion'))
    );
  }

  deleteConversion(type: TypeConversion) {
    const httpOptions = {
      headers: new HttpHeaders()
    };
    const url = `${this.typeConversionUrl}/${type.id}`;

    return this.http.delete<TypeConversion>(url, httpOptions).pipe(
      tap(_ => this.log(`deleted conversion id=${type.id}`)),
      catchError(this.handleError<TypeConversion>('deleteConversion'))
    );
  }

  searchTypeConversion(term: string): Observable<TypeConversion[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<TypeConversion[]>(`${this.typeConversionUrl}/?name=${term}`).pipe(
      tap(x => x.length ?
        this.log(`found conversions matching "${term}"`) :
        this.log(`no conversions matching "${term}"`)),
      catchError(this.handleError<TypeConversion[]>('searchConversion', []))
    );
  }

}
