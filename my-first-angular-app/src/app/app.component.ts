import { Component, DoCheck } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-root',
    templateUrl: 'app.component.html',
    styleUrls: ['app.component.css']
})
export class AppComponent implements DoCheck {
    pageTitle = 'mCart';
    loginTitle = 'Login';
    userName: string;
    constructor(private _router: Router) { }

    ngDoCheck(): void {
        this.userName = sessionStorage.getItem('username');
    }

    login() {
        const value = document.getElementById('login').innerHTML;

        if (value === 'Login') {
            this._router.navigate(['/login']);
        } else if (value === 'Logout') {
            sessionStorage.clear();
            document.getElementById('login').innerHTML = 'Login';
            document.getElementById('welcome').style.display = 'none';
            this._router.navigate(['/welcome']);
        }
    }
}
