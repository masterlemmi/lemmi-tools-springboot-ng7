import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from 'app/core/message.service';
import {Shortcut } from './shortcut'

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({ providedIn: 'root' })
export class ShortcutService {

  private shortcutsUrl = 'api/ide/shortcuts';  // URL to web api

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET shortcuts from the server */
  getAll (): Observable<Shortcut[]> {
    return this.http.get<Shortcut[]>(`${this.shortcutsUrl}`)
      .pipe(
        tap(_ => this.log('fetched shortcuts')),
        catchError(this.handleError('getShortcutes', []))
      );
  }

  /** GET shortcut by id. Will 404 if id not found */
  getShortcut(id: number): Observable<Shortcut> {
    const url = `${this.shortcutsUrl}/${id}`;
    return this.http.get<Shortcut>(url).pipe(
      tap(_ => this.log(`fetched shortcut id=${id}`)),
      catchError(this.handleError<Shortcut>(`getShortcut id=${id}`))
    );
  }

  /* GET shortcuts whose name contains search term */
  searchShortcutes(term: string): Observable<Shortcut[]> {
    console.log("FINDING.. " + term)
    if (!term.trim()) {
      // if not search term, return empty shortcut array.
      return of([]);
    }``
    return this.http.get<Shortcut[]>(`${this.shortcutsUrl}/find?name=${term}`).pipe(
      tap(_ => this.log(`found shortcuts matching "${term}"`)),
      catchError(this.handleError<Shortcut[]>('searchShortcutes', []))
    );
  }

  //////// Save methods //////////

  /** POST: add a new shortcut to the server */
  addShortcut (shortcut: Shortcut): Observable<Shortcut> {
    return this.http.post<Shortcut>(this.shortcutsUrl, shortcut, httpOptions).pipe(
      tap((shortcut: Shortcut) => this.log(`added shortcut w/ id=${shortcut.id}`)),
      catchError(this.handleError<Shortcut>('addShortcut'))
    );
  }

  /** DELETE: delete the shortcut from the server */
  deleteShortcut (shortcut: Shortcut | number): Observable<Shortcut> {
    const id = typeof shortcut === 'number' ? shortcut : shortcut.id;
    const url = `${this.shortcutsUrl}/${id}`;

    return this.http.delete<Shortcut>(url, httpOptions).pipe(
      tap(_ => this.log(`deleted shortcut id=${id}`)),
      catchError(this.handleError<Shortcut>('deleteShortcut'))
    );
  }

  /** PUT: update the shortcut on the server */
  updateShortcut (shortcut: Shortcut): Observable<any> {
    return this.http.put(this.shortcutsUrl, shortcut, httpOptions).pipe(
      tap(_ => this.log(`updated shortcut id=${shortcut.id}`)),
      catchError(this.handleError<any>('updateShortcut'))
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

  /** Log a ShortcutService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`ShortcutService: ${message}`);
  }
}