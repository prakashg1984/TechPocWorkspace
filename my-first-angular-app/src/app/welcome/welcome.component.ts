import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    templateUrl: 'welcome.component.html',
    styleUrls: ['welcome.component.css']
})
export class WelcomeComponent {
    public pageTitle = 'Survey';
    loginTitle = 'Login';

  constructor(private _router: Router) {
        document.getElementById('login').style.display = '';
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
