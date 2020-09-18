import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Login } from './login';

@Injectable()
export class LoginService {
    username: string;
    constructor(private http: HttpClient) { }

    getUsers(): Observable<Login[]> {
         return this.http.get<Login[]>('assets/users/users.json').pipe(
            catchError(this.handleError));
    }

     private handleError(err: HttpErrorResponse) {
        console.error(err);
        return Observable.throw(err.error() || 'Server error');
    }
}
