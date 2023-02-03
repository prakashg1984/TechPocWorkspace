import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Login } from './Login';
import { LoginService } from './login.service';

@Component({
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
    providers: [LoginService]
})
export class LoginComponent {

    login = new Login();

    users: any[];
    valid = false;
    isLoggedIn = 'false';
    loginForm: any;

    constructor(private router: Router, private loginService: LoginService) {
        document.getElementById('login').style.display = 'none';
        /*this.loginService.getUsers()
            .subscribe(users => this.users = users);*/
    }

    onSubmit() {
        this.valid = true;
        const name = this.login.userName;
        sessionStorage.setItem('username', this.login.userName);
        const password = this.login.password;

      console.log("Invoking validateUser");
      this.loginService.validateUser(name, password).subscribe((data) => {
        console.log(data);

        if (data) {
          this.isLoggedIn = 'true';
          sessionStorage.setItem('isLoggedIn', this.isLoggedIn);
          sessionStorage.setItem('loggedInUser', name);
          this.router.navigate(['/hello']);
        } else {
          this.isLoggedIn = 'false';
          sessionStorage.setItem('isLoggedIn', this.isLoggedIn);
          this.valid = false;
        }
      }
      );

       /* const user = this.users.filter(currUser => currUser.userName === name && currUser.password === password)[0];
        if (user) {
            this.isLoggedIn = 'true';
          sessionStorage.setItem('isLoggedIn', this.isLoggedIn);
          sessionStorage.setItem('loggedInUser', name);
            this.router.navigate(['/hello']);
        } else {
            this.isLoggedIn = 'false';
            sessionStorage.setItem('isLoggedIn', this.isLoggedIn);
            this.valid = false;
        }*/
  }

  onRegister() {
    this.router.navigate(['/register']);
  }
}
