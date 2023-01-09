import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Login } from './login';

@Injectable()
export class LoginService {
    username: string;
  constructor(private http: HttpClient) {
    
  }

  getBaseUrl() {
    return 'http://172.18.0.1:8080';
  }

 /*   getUsers(): Observable<Login[]> {
         return this.http.get<Login[]>('assets/users/users.json').pipe(
            catchError(this.handleError));
  }*/

  public validateUser(userName, passWord) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Control-Allow-Origin': '*'
      })
    };
    return this.http.get('/getUser/' + userName + '/' + passWord, httpOptions);
  }

     private handleError(err: HttpErrorResponse) {
        console.error(err);
        return Observable.throw(err.error() || 'Server error');
    }
}
