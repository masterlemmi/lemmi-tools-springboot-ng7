import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from 'app/core/message.service';
import {Plugin } from './plugin'

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({ providedIn: 'root' })
export class PluginService {

  private pluginsUrl = 'api/ide/plugins';  // URL to web api

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET plugins from the server */
  getAll (): Observable<Plugin[]> {
    return this.http.get<Plugin[]>(`${this.pluginsUrl}`)
      .pipe(
        tap(_ => this.log('fetched plugins')),
        catchError(this.handleError('getPlugines', []))
      );
  }

  /** GET plugin by id. Will 404 if id not found */
  getPlugin(id: number): Observable<Plugin> {
    const url = `${this.pluginsUrl}/${id}`;
    return this.http.get<Plugin>(url).pipe(
      tap(_ => this.log(`fetched plugin id=${id}`)),
      catchError(this.handleError<Plugin>(`getPlugin id=${id}`))
    );
  }

  /* GET plugins whose name contains search term */
  searchPlugines(term: string): Observable<Plugin[]> {
    console.log("FINDING.. " + term)
    if (!term.trim()) {
      // if not search term, return empty plugin array.
      return of([]);
    }``
    return this.http.get<Plugin[]>(`${this.pluginsUrl}/find?name=${term}`).pipe(
      tap(_ => this.log(`found plugins matching "${term}"`)),
      catchError(this.handleError<Plugin[]>('searchPlugines', []))
    );
  }

  //////// Save methods //////////

  /** POST: add a new plugin to the server */
  addPlugin (plugin: Plugin): Observable<Plugin> {
    return this.http.post<Plugin>(this.pluginsUrl, plugin, httpOptions).pipe(
      tap((plugin: Plugin) => this.log(`added plugin w/ id=${plugin.id}`)),
      catchError(this.handleError<Plugin>('addPlugin'))
    );
  }

  /** DELETE: delete the plugin from the server */
  deletePlugin (plugin: Plugin | number): Observable<Plugin> {
    const id = typeof plugin === 'number' ? plugin : plugin.id;
    const url = `${this.pluginsUrl}/${id}`;

    return this.http.delete<Plugin>(url, httpOptions).pipe(
      tap(_ => this.log(`deleted plugin id=${id}`)),
      catchError(this.handleError<Plugin>('deletePlugin'))
    );
  }

  /** PUT: update the plugin on the server */
  updatePlugin (plugin: Plugin): Observable<any> {
    return this.http.put(this.pluginsUrl, plugin, httpOptions).pipe(
      tap(_ => this.log(`updated plugin id=${plugin.id}`)),
      catchError(this.handleError<any>('updatePlugin'))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a PluginService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`PluginService: ${message}`);
  }
}